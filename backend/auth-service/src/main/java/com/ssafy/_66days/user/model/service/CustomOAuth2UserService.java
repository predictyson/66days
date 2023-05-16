package com.ssafy._66days.user.model.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);    // 유저 정보 가져오기
		Map<String, Object> attributes = oAuth2User.getAttributes();    // 소셜 로그인 리소스 서버가 제공하는 유저 정보

		log.info("attributes: {}", attributes);

		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		String email = (String)kakaoAccount.get("email");
		String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		log.info("KAKAOAccount: {}", kakaoAccount);

		String userNameAttributeName = oAuth2UserRequest
			.getClientRegistration()
			.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();    // 카카오 회원 번호

		log.info("userNameAttributeName: {}", userNameAttributeName);

		Optional<User> userOptional = userRepository.findByEmailAndProvider(email, provider);
		UUID uuid = null;
		Role role;

		if (userOptional.isPresent()) {
			// 저장되어 있는 인증 정보인데
			if (userOptional.get().getRole().equals(Role.ROLE_USER)) {
				// 우리 서비스에 등록된 회원이면 USER 권한
				role = Role.ROLE_USER;
				uuid = userOptional.get().getUserId();
				log.info("기존 회원: {}", role);

			} else {
				// 우리 서비스에 등록되지 않은/가입한 한 카카오 회원이면 ROLE_ANONYMOUS 권한
				role = Role.ROLE_ANONYMOUS;

				log.info("가입 미완료 회원: {}", role);
			}
		} else {
			// 아예 저장되어있지 않은 인증 정보
			role = Role.ROLE_ANONYMOUS;

			log.info("처음 방문한 사용자: {}", role);
		}

		return new CustomOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(String.valueOf(role))),
			attributes,
			userNameAttributeName,
			uuid,
			email,
			provider,
			role
		);
	}
}