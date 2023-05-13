package com.ssafy._66days.user.model.dto;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.ssafy._66days.user.enums.Role;

import lombok.Getter;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

	private final UUID uuid;
	private final String email;
	private final String provider;
	private final Role role;

	public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
							Map<String, Object> attributes,
							String nameAttributeKey,
							UUID uuid,
							String email,
							String provider,
							Role role) {
		super(authorities, attributes, nameAttributeKey);
		this.uuid = uuid;
		this.email = email;
		this.provider = provider;
		this.role = role;
	}
}
