package com.ssafy._66days.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy._66days.security.JwtAccessDeniedHandler;
import com.ssafy._66days.security.JwtAuthenticationEntryPoint;
import com.ssafy._66days.security.JwtAuthenticationFilter;
import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.security.OAuth2LoginFailureHandler;
import com.ssafy._66days.security.OAuth2LoginSuccessHandler;
import com.ssafy._66days.user.model.service.CustomOAuth2UserService;
import com.ssafy._66days.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomOAuth2UserService customOAuth2UserService;
	private final RedisUtil redisUtil;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final JwtProvider jwtProvider;

	private static final String[] PERMIT_URL_ARRAY = {
			/* swagger v2 */
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			/* swagger v3 */
			"/v3/api-docs/**",
			"/swagger-ui/**"
	};

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic().disable()
				.csrf().disable()
				.cors().configurationSource(corsConfigurationSource())
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(PERMIT_URL_ARRAY).permitAll()
				.anyRequest().authenticated()
				.and()
				.oauth2Login()
				.userInfoEndpoint().userService(customOAuth2UserService)
				.and()
				.successHandler(oAuth2LoginSuccessHandler)
				.failureHandler(oAuth2LoginFailureHandler)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)
				.and()    // 필터 체인에 커스텀 필터 추가 설정
				.addFilterBefore(
						new JwtAuthenticationFilter(jwtProvider, redisUtil),
						UsernamePasswordAuthenticationFilter.class
				);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		// configuration.addAllowedOrigin("http://localhost:???");
		configuration.addAllowedOriginPattern("*");
//		configuration.addAllowedOrigin("https://k8a705.p.ssafy.io");
//		configuration.addAllowedOrigin("https://accounts.kakao.com");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
