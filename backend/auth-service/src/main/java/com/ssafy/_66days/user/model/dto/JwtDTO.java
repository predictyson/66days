package com.ssafy._66days.user.model.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class JwtDTO {
	private String token;

	public JwtDTO(String token) {
		this.token = token;
	}
}
