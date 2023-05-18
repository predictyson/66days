package com.ssafy._66days.util;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ssafy._66days.security.UserPrincipal;

@Component
public class SecurityUtil {

	public Optional<UUID> getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Optional.empty();
		}

		UUID userId = null;
		if (authentication.getPrincipal() instanceof UserPrincipal) {
			UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
			userId = userPrincipal.getUserId();
		} else if (authentication.getPrincipal() instanceof UUID) {
			userId = (UUID)authentication.getPrincipal();
		}
		return Optional.ofNullable(userId);
	}
}
