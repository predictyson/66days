package com.ssafy._66days.user.model.service;

import com.ssafy._66days.exception.UserNotFoundException;
import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.model.dto.UserRequestDTO;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.util.RedisUtil;
import com.ssafy._66days.util.Utils;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

	private static final String SIGN_UP_URI = "/signup";
	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
	private final RedisUtil redisUtil;
	@Value("${redirectUrl}")
	private String redirectUrl;

	/**
	 * OAuth2 로그인 후, 회원 가입에 필요한 추가 정보를 받기 위해 redirect
	 */
	public void redirectSignupPage(HttpServletResponse response, Authentication authentication) {
		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		String accessToken = jwtProvider.createTokensFromAuthentication(authentication, Utils.ACCESS_TOKEN);

		log.info("accessToken: {}", accessToken);
		try {
			String tokenParameter = Utils.getQueryParameter(Utils.TOKEN, Utils.BEARER_TOKEN_PREFIX + accessToken);
			String redirectUri = new StringBuilder()
					.append(redirectUrl)
					.append(SIGN_UP_URI)
					.append(Utils.QUESTION_MARK)
					.append(tokenParameter)
					.append(Utils.AMPERSAND)
					.append(Utils.EMAIL)
					.append(Utils.EQUALS_SIGN)
					.append(oAuth2User.getEmail())
					.append(Utils.AMPERSAND)
					.append("profileImagePath")
					.append(Utils.EQUALS_SIGN)
					.append(((Map<String, Object>)oAuth2User.getAttributes().get("properties"))
							.get("profile_image")
							.toString())
					.toString();

			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * OAuth2 로그인 후, Main Page redirect
	 */
	public void login(HttpServletResponse response, Authentication authentication) {
		String accessToken = jwtProvider.createTokensFromAuthentication(authentication, Utils.ACCESS_TOKEN);
		jwtProvider.createTokensFromAuthentication(authentication, Utils.REFRESH_TOKEN);

		log.info("accessToken: {}", accessToken);

		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		SecurityContextHolder.getContext()
				.setAuthentication(
						new UsernamePasswordAuthenticationToken(
								oAuth2User, accessToken, oAuth2User.getAuthorities()
						)
				);
		try {
			String tokenParameter = Utils.getQueryParameter(Utils.TOKEN, Utils.BEARER_TOKEN_PREFIX + accessToken);
			String redirectUri = new StringBuilder()
					.append(redirectUrl)
					.append(Utils.QUESTION_MARK)
					.append(tokenParameter)
					.toString();

			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 로그아웃
	 */
	@Transactional
	public void logout(String token) {
		UUID userId = jwtProvider.getUserIdFromJwt(token);
		String key = Utils.RT_COLON + Encoders.BASE64.encode(userId.toString().getBytes());
		if (redisUtil.getData(key) != null) {
			redisUtil.deleteData(key);
		}
		long expiration = jwtProvider.getExpiration(token);
		redisUtil.setDataExpire(token, token, expiration);
		SecurityContextHolder.getContext().setAuthentication(null);
		log.info("로그아웃 유저 UUID : '{}'", userId);
	}

	/**
	 * 가입 여부 확인
	 */
	public boolean isRegisteredUser(UserRequestDTO userRequestDTO) {
		String email = userRequestDTO.getEmail();
		Provider provider = userRequestDTO.getProvider();
		return userRepository.findByEmailAndProvider(email, provider).isPresent();
	}

	/**
	 * 회원 탈퇴
	 */
	@Transactional
	public void withdraw(String token) {
		UUID userId = jwtProvider.getUserIdFromJwt(token);
		User user = userRepository
				.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("가입되지 않은 사용자입니다."));
		String key = Utils.RT_COLON + Encoders.BASE64.encode(userId.toString().getBytes());
		if (redisUtil.getData(key) != null) {
			redisUtil.deleteData(key);
		}
		user.withdraw();
	}

	/**
	 * 가입 완료 처리: ROLE_ANONYMOUS -> ROLE_USER
	 */
	@Transactional
	public void signup(UUID userId) {
		User user = userRepository
				.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("가입되지 않은 사용자입니다."));
		jwtProvider.createTokensFromUserId(userId, Utils.REFRESH_TOKEN);
		user.signup();
		userRepository.save(user);
	}

	/**
	 * Auth 서버에 유저 등록
	 */
	@Transactional
	public void signup(UserRequestDTO userRequestDTO) {
		if (!isRegisteredUser(userRequestDTO)) {
			User user = userRequestDTO.toEntity();
			userRepository.save(user);
		}
	}
}
