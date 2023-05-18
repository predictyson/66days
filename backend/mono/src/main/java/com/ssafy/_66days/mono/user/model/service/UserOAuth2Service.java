package com.ssafy._66days.mono.user.model.service;

import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import com.ssafy._66days.mono.user.vo.OAuthAttributes;
import com.ssafy._66days.mono.user.vo.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserOAuth2Service extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(
			OAuth2UserRequest userRequest
			) throws OAuth2AuthenticationException {
		log.info("oauth service in");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId(); // ex) naver
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        
        User user = userRepository.findByEmail(attributes.getEmail()).orElse(null);
        if (user == null) {
			log.info("oauth new");
        	user = User.builder()
        			.email(attributes.getEmail())
        			.nickname(attributes.getName())
        			.social(attributes.getSocial())
        			.build();
        	return UserPrincipal.create(user);
        } else {
        	// already regist
        	if (user.getSocial() != attributes.getSocial()) {
        		log.info("oauth disable");
        		return UserPrincipal.disable();
        	}
        	// 이미 가입 처리 되어 있고 로그인만 시켜주면 된다는 의미를 보내야 함.
        	log.info("oauth login");
        	return UserPrincipal.login(user);
        }
	}

}