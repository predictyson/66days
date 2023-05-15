package com.ssafy._66days.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;
import com.ssafy._66days.user.model.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	// Sign up for 66days service
	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestHeader("Aauthorization") String authorization,
			@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
		// 토큰 보내서 확인해오고 유저아이디 방아온다.
		// UUID userId
		// 서비스 로직에서 UUID 로 이메일 찾고, serSignUpRequestDTO 대로 가입하기.
		// 마지막에 ROLE 변경해야(가입 확정됐다는 의미)
		return ResponseEntity.ok().build();
	}

	@GetMapping("/check-nickname/{nickname}")
	public ResponseEntity<Void> isNicknameAvailable(@RequestHeader("Authorization") String token,
			@PathVariable String nickname) {
		// get UUID from token?
		// userService.
		return ResponseEntity.ok().build();
	}

	// @GetMapping
	// public ResponseEntity<UserDetailResponseDTO> getUserDetail(@RequestHeader("Authorization") String token) {
		// get UUID from token
		//UserDetailResponseDTO userDetailResponseDTO = userService.getUserDetail(uuid);
		//return ResponseEntity.ok(userDetailResponseDTO);
		// return ResponseEntity.ok().build();
	// }

}
