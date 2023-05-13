package com.ssafy._66days.user.model.dto;

import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

	private final String email;

	private final String provider;

	private final Role role;

	@Builder
	public UserDTO(String email, String provider) {
		this.email = email;
		this.provider = provider;
		this.role = Role.ROLE_ANONYMOUS;
	}

	public User toEntity() {
		return User
			.builder()
			.email(email)
			.provider(provider)
			.role(role)
			.build();
	}
}
