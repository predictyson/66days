package com.ssafy._66days.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.ssafy._66days.user.enums.Provider;
import com.ssafy._66days.user.enums.Role;
import com.ssafy._66days.user.model.entity.User;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserPrincipal implements UserDetails, OAuth2User {

	private UUID userId;
	private String email;
	private Provider provider;
	private Role role;
	private Collection<SimpleGrantedAuthority> authorities;
	private Map<String, Object> attributes;

	@Builder
	public UserPrincipal(UUID userId,
						 String email,
						 Provider provider,
						 Role role,
						 Collection<SimpleGrantedAuthority> authorities,
						 Map<String, Object> attributes) {
		this.userId = userId;
		this.email = email;
		this.provider = provider;
		this.role = role;
		this.authorities = authorities;
		this.attributes = attributes;
	}

	/**
	 * User -> UserPrincipal
	 */
	public static UserPrincipal createUserPrincipal(User user) {
		return UserPrincipal.builder()
			.userId(user.getUserId())
			.email(user.getEmail())
			.provider(user.getProvider())
			.role(user.getRole())
			.authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
			.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> String.valueOf(role));
		return authorities;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public Provider getProvider() {
		return provider;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
