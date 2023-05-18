package com.ssafy._66days.user.model.dto;

import java.util.UUID;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequestDTO {

	private final UUID userId;

	private final String email;

	private final Provider provider;

	private final Role role;

	@Builder
	public UserRequestDTO(UUID userId, String email, Provider provider) {
		this.userId = userId;
		this.email = email;
		this.provider = provider;
		this.role = Role.ROLE_ANONYMOUS;
	}

	public User toEntity() {
		return User.builder()
				.userId(userId)
				.email(email)
				.provider(provider)
				.role(role)
				.build();
	}
}
