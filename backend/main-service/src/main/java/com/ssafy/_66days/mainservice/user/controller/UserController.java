package com.ssafy._66days.mainservice.user.controller;

import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import com.ssafy._66days.mainservice.user.model.service.UserService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.ssafy._66days.user.model.dto.UserDetailResponseDTO;
//import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final UserService userService;
	private final AuthServiceClient authServiceClient;

	@GetMapping("/uuid/{uuid}")
	public ResponseEntity<UUID> extractUUIDFromToken(@PathVariable UUID uuid) {
		log.info("User-service UserController: {}",uuid);
		ResponseEntity<UUID> response = null;
		try {
			response = authServiceClient.extractUUID(uuid);
		} catch (FeignException e){
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(response.getBody());
	}

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
