package com.ssafy._66days.user.controller;

import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.user.model.dto.JwtDTO;
import com.ssafy._66days.user.model.service.UserService;
import com.ssafy._66days.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	private final UserService userService;
	private final JwtProvider jwtProvider;
	Environment env;

	@GetMapping("/health_check")
	public String check(HttpServletRequest request) {
		log.info("Server port={}", request.getServerPort());
		return String.format("Hi, there. This is a message from Auth service");
	}

	// TEST Code : UUID GET FROM TOKEN.
	@GetMapping("/uuid/{uuid}")
	public ResponseEntity<UUID> extractUUID(@PathVariable UUID uuid) {
		return ResponseEntity.ok(uuid);
	}

	/**
	 * Token 재발급 API
	 */
	@PostMapping("/token/reissue")
	public ResponseEntity<JwtDTO> tokenReissue(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		JwtDTO tokenDto = jwtProvider.reissue(token);
		return ResponseEntity.ok().body(tokenDto);
	}

	// UUID GET FROM TOKEN.
	@GetMapping("/token/uuid")
	public ResponseEntity<UUID> extractUUIDFromToken(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		UUID uuid = jwtProvider.getUserIdFromJwt(token);
		return ResponseEntity.ok(uuid);
	}

	/**
	 * 로그아웃 API
	 */
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		userService.logout(token);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 회원 탈퇴 API
	 */
	@PostMapping("/{user-id}/withdrawal")
	public ResponseEntity<Void> withdraw(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		userService.withdraw(token);
		return ResponseEntity.noContent().build();
	}

	// Auth 서버에 user role 변경
	@PutMapping("/signup")
	public ResponseEntity<Void> signup(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		UUID userId = jwtProvider.getUserIdFromJwt(token);
		userService.signup(userId);
		return ResponseEntity.ok().build();
	}
}
