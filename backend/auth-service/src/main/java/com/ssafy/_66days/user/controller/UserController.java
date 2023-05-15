package com.ssafy._66days.user.controller;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.user.model.dto.JwtDTO;
import com.ssafy._66days.user.model.service.UserService;
import com.ssafy._66days.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
		System.out.println(uuid);
		return ResponseEntity.ok(uuid);
	}

	// token reissue
	@PostMapping("/token/reissue")
	public ResponseEntity<JwtDTO> tokenReissue(@RequestHeader(Utils.AUTHORIZATION) String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		JwtDTO tokenDto = jwtProvider.reissue(token);
		return ResponseEntity.ok().body(tokenDto);
	}

	// UUID GET FROM TOKEN.
	@GetMapping("/token/uuid")
	public ResponseEntity<UUID> extractUUIDFromToken(@RequestHeader(Utils.AUTHORIZATION) String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		System.out.println(token);
		UUID uuid = jwtProvider.getUserIdFromJwt(token);
		return ResponseEntity.ok(uuid);
	}

	// logout
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader(Utils.AUTHORIZATION) String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		userService.logout(token);
		return ResponseEntity.noContent().build();
	}


	@PostMapping("/{user-id}/withdrawal")
	public ResponseEntity<Void> withdraw(@RequestHeader(Utils.AUTHORIZATION) String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		userService.withdraw(token);
		return ResponseEntity.noContent().build();
	}
}
