package com.ssafy._66days.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.model.dto.UserDTO;
import com.ssafy._66days.user.model.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) {
		CustomOAuth2User customOAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		Role role = customOAuth2User.getRole();
		String email = customOAuth2User.getEmail();
		String provider =customOAuth2User.getProvider();
		UserDTO userDTO = new UserDTO(email, provider);

		log.info("role: {}", role);

		if (userService.isRegisteredUser(userDTO)) {
			// 사용자 정보가 DB에 있다면(카카오로 로그인/회원가입한 적이 있따면)
			if (role == Role.ROLE_ANONYMOUS) {
				// 하지만 서비스 가입까지 이어지지 않았따면 닉네임 입력받도록 리다이렉트
				log.info("Proceed to membership registration.");
				userService.redirectSignupPage(response, authentication, email);
			} else if (role == Role.ROLE_USER) {
				// 서비스 가입까지 한 상태면 로그인하고 어딘가로 리다이렉트
				log.info("Proceed to login.");
				userService.login(response, authentication);
			}
		} else {
			// auth db에 저장해놓고 닉네임 입력받도록 리다이렉트
			log.info("Create basic user information.");
			userService.signup(userDTO);
			log.info("Proceed to membership registration.");
			userService.redirectSignupPage(response, authentication, email);
		}
	}
}
