package com.ssafy._66days.security;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.dto.CustomOAuth2User;
import com.ssafy._66days.user.model.dto.UserRequestDTO;
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
		UUID userId = customOAuth2User.getUserId();
		String email = customOAuth2User.getEmail();
		Provider provider = customOAuth2User.getProvider();
		UserRequestDTO userRequestDTO = new UserRequestDTO(userId, email, provider);

		log.info("role: {}", role);

		if (userService.isRegisteredUser(userRequestDTO)) {
			if (role == Role.ROLE_ANONYMOUS) {
				log.info("Proceed to membership registration.");
				userService.redirectSignupPage(response, authentication);
			} else if (role == Role.ROLE_USER) {
				log.info("Proceed to login.");
				userService.login(response, authentication);
			}
		} else {
			log.info("Create basic user information.");
			userService.signup(userRequestDTO);
			log.info("Proceed to membership registration.");
			userService.redirectSignupPage(response, authentication);
		}
	}
}
