package com.ssafy._66days.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ssafy._66days.util.RedisUtil;
import com.ssafy._66days.util.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final JwtProvider jwtProvider;
	private final RedisUtil redisUtil;

	@Override
	public void doFilter(ServletRequest request,
						 ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String requestURI = httpServletRequest.getRequestURI();
		String accessToken = getTokenFromRequest(httpServletRequest);

		if (StringUtils.hasText(accessToken) && redisUtil.getData(accessToken) == null) {
			if (jwtProvider.validateToken(accessToken)) {
				Authentication authentication = jwtProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info(
					"Stored authentication information({}) in the Security Context. uri: {}",
					authentication.getName(),
					requestURI
				);
			} else {
				log.info("Invalid access token. uri: {}", requestURI);
			}
		} else {
			log.info("Login is required. uri: {}", requestURI);
		}
		chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(Utils.AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Utils.BEARER_TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
