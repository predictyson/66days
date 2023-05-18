package com.ssafy._66days.mono.user.model.service;

import java.util.Map;
import java.util.UUID;

public interface JwtService {
	<T> String createAccessToken(UUID userId);
	<T> String createAccessToken(UUID userId, String role);
	<T> String create(UUID userId, String subject, int expir);
	<T> String create(UUID userId, String subject, int expir, String role);
	void validateToken(String jwtToken);
	UUID getUserId(String jwtToken);
	Map<String, Object> get(String key);
}
