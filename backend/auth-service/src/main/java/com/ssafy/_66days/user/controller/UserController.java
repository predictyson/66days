package com.ssafy._66days.user.controller;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy._66days.security.JwtProvider;
import com.ssafy._66days.user.model.dto.JwtDTO;
import com.ssafy._66days.user.model.service.UserService;
import com.ssafy._66days.util.Utils;

import lombok.RequiredArgsConstructor;

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
		return String.format("Hi, there. This is a message from Chat Service on PORT %s", env.getProperty("local.server.port"));
	}

	// token reissue
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

	// logout
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorization) {
		String token = authorization.split(Utils.BLANK)[1];
		userService.logout(token);
		return ResponseEntity.noContent().build();
	}

	// Sign up for 66days service
	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestHeader("Authorization") String authorization,
									   @RequestBody String nickname) {
		String token = authorization.split(Utils.BLANK)[1];
		UUID userId = jwtProvider.getUserIdFromJwt(token);
		// RequestBody 바꿔야함 객체 ㅇㅇ..
		// 서비스 로직에서 UUID 로 이메일 찾고, 같이 넣어서 API CALL(해당 API에서는 TOKEN 추출하는거 하지 말기. 그냥 받아서 쓰기)
		// 마지막에 ROLE 변경해야(가입 확정됐다는 의미)
		return ResponseEntity.ok().build();
	}
}
