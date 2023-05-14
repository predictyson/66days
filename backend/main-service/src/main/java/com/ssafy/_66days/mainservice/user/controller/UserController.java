package com.ssafy._66days.mainservice.user.controller;

import com.ssafy._66days.mainservice.user.model.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.ssafy._66days.user.model.dto.UserDetailResponseDTO;
//import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;

import lombok.RequiredArgsConstructor;

//@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	// 회원가입
//	@PostMapping("/signup")
//	public ResponseEntity<Void> createUser(@RequestHeader("Authorization") String token,
//		@RequestBody UserSignUpRequestDTO userSignUpRequestDto) {
//		// get UUID from token
//
//		// in service logic, 티어 id는 정해져 있겠지만 .. 동물 id는 ㅇㅋ..뽑아야함.
//		return ResponseEntity.ok().build();
//	}

	//
	@GetMapping("/check-nickname/{nickname}")
	public ResponseEntity<Void> isNicknameAvailable(@RequestHeader("Authorization") String token,
		@PathVariable String nickname) {
		// get UUID from token?
		// userService.
		return ResponseEntity.ok().build();
	}

//	@GetMapping
//	public ResponseEntity<UserDetailResponseDTO> getUserDetail(@RequestHeader("Authorization") String token) {
//		// get UUID from token
//		//UserDetailResponseDTO userDetailResponseDTO = userService.getUserDetail(uuid);
//		//return ResponseEntity.ok(userDetailResponseDTO);
//		return ResponseEntity.ok().build();
//	}
}
