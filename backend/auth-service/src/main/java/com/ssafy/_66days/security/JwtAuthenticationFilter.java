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
		// 토큰 얻어오고 요청 URI 겟
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String accessToken = getTokenFromRequest(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();

		if (StringUtils.hasText(accessToken) && redisUtil.getData(accessToken) == null) {
			// 액세스 토큰이 있고, 액세스 토큰이 레디스에 있으면 => 로그인 상태이면
			if (jwtProvider.validateToken(accessToken)) {
				// 토큰 유효 시 컨텍스트에 저장(써먹어야하니까)
				Authentication authentication = jwtProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info(
					"Stored authentication information({}) in the Security Context. uri: {}",
					authentication.getName(),
					requestURI
				);
			} else {
				// 토큰 무효면 일단 냅두기(재발급받아야하니까)
				log.info("Invalid access token. uri: {}", requestURI);
			}
		} else {
			// 로그아웃된 사람(로그인해야됨)
			log.info("Login is required. uri: {}", requestURI);
		}
		chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7);
		} else {
			return null;
		}
	}
}
