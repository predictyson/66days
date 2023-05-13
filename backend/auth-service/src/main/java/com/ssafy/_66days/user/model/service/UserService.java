package com.ssafy._66days.user.model.service;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.model.dto.UserDTO;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.util.RedisUtil;
import com.ssafy._66days.util.Utils;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

	@Value("${redirectUrl}")
	private String redirectUrl;
	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
//	private final SecurityUtil securityUtil;
	private final RedisUtil redisUtil;

	//
	private static final String SIGN_UP_URI = "/signup";

	/**
	 * OAuth 로그인 후, 회원 가입에 필요한 추가 정보를 받기 위해 redirect
	 * return token in query parameter
	 */
	public void redirectSignupPage(HttpServletResponse response, Authentication authentication, String email) {
		String accessToken = jwtProvider.createTokensFromAuthentication(authentication, "AccessToken");
		log.info("accessToken: {}", accessToken);
		try {
			String tokenParameter = "token=Bearer " + accessToken;
			String redirectUri = new StringBuilder()
				.append(redirectUrl)
				.append(SIGN_UP_URI)
				.append('?')
				.append(tokenParameter)
				.append('&')
				.append("email=")
				.append(email)
				.toString();
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(redirectUri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 카카오 로그인
	 * return token, userId, nickname in query parameter
	 */
	public void login(HttpServletResponse response, Authentication authentication) {

		// 토큰 발급 후 SecurityContext에 인증 정보 저장
		String accessToken = jwtProvider.createTokensFromAuthentication(authentication, "AccessToken");
		String refreshToken = jwtProvider.createTokensFromAuthentication(authentication, "RefreshToken");

		log.info("accessToken: {}", accessToken);

		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		SecurityContextHolder.getContext()
			.setAuthentication(
				new UsernamePasswordAuthenticationToken(
					oAuth2User, accessToken, oAuth2User.getAuthorities()
				)
			);

		try {
			User user = userRepository.findById(oAuth2User.getUuid()).orElse(null);

			String tokenParameter = Utils.getQueryParameter(Utils.TOKEN, Utils.BEARER_TOKEN_PREFIX + accessToken);
			// String userIdParameter = Utils.getQueryParameter(Utils.USER_ID, oAuth2User.getUserId().toString());
			// String nicknameParameter = Utils.getQueryParameter(Utils.NICKNAME,
			// 	URLEncoder.encode(user.getNickname(), Utils.UTF_8));

			String redirectUri = new StringBuilder()
				.append(redirectUrl)           // server domain
				.append(Utils.QUESTION_MARK)   // ?
				.append(tokenParameter)        // token=Bearer 1q2w3e4r5
				.toString();

			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(redirectUri);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void logout(String token) {
		// token에서 정보 가져오기
		// Claims claims = jwtProvider.parseClaims(token);
		UUID userId = jwtProvider.getUserIdFromJwt(token);

		String key = "RT:" + Encoders.BASE64.encode(userId.toString().getBytes());
		if (redisUtil.getData(key) != null) {
			redisUtil.deleteData(key);
		}
		long expiration = jwtProvider.getExpiration(token);
		redisUtil.setDataExpire(token, token, expiration);
		SecurityContextHolder.getContext().setAuthentication(null);

		// log.info("로그아웃 유저 이메일 : '{}'", jwtProvider.getEmailFromClaims(claims));
	}

	public boolean isRegisteredUser(UserDTO userDTO) {
		String email = userDTO.getEmail();
		String provider = userDTO.getProvider();
		return userRepository.findByEmailAndProvider(email, provider).isPresent();
	}

	@Transactional(rollbackFor = Exception.class)
	public void signup(UserDTO userDTO) {
		if (isRegisteredUser(userDTO)) {
			// 에러 처리해야.
			return;
		}
		User user = userDTO.toEntity();
		userRepository.save(user);
	}
}
