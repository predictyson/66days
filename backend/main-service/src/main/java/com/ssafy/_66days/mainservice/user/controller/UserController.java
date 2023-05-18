package com.ssafy._66days.mainservice.user.controller;

import com.ssafy._66days.mainservice.page.model.dto.MainPageResponseDTO;
import com.ssafy._66days.mainservice.page.model.service.PageService;
import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailResponseDTO;
import com.ssafy._66days.mainservice.user.model.dto.UserSignUpRequestDTO;
import com.ssafy._66days.mainservice.user.model.service.UserService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.ssafy._66days.user.model.dto.UserDetailResponseDTO;
//import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

	private final UserService userService;
	private final AuthServiceClient authServiceClient;
	private final PageService pageService;

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
	@PatchMapping("/modify/{nickname}")
	public ResponseEntity<Boolean> modifyNickname(@RequestHeader("Authorization") String token,
											  @RequestParam(value = "nickname") String nickname
											  ) {

		// get UUID from token?
		UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
		log.info("Modify nickname userId: {}",userId);

		try {
			userService.modifyNickname(userId, nickname);
		} catch (Exception e){
			log.error(e.getMessage());
			return ResponseEntity.ok(false);
		}

		return ResponseEntity.ok(true);
	}

	@PatchMapping("/modify/image")
	public ResponseEntity<Boolean> modifyImage(@RequestHeader("Authorization") String token,
												  @RequestPart(value = "image") MultipartFile image
	) {

		// get UUID from token?
		UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
		log.info("Modify nickname userId: {}",userId);

		try {
			userService.modifyPofileImage(userId, image);
		} catch (Exception e){
			log.error(e.getMessage());
			return ResponseEntity.ok(false);
		}

		return ResponseEntity.ok(true);
	}

	@GetMapping("/check-nickname/{nickname}")
	public ResponseEntity<Boolean> isNicknameAvailable(@RequestHeader("Authorization") String token,
													   @PathVariable String nickname) {
		//token validation
		// get UUID from token?
		UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
		// userService.
		boolean isAvailable = userService.isNicknameAvailable(nickname);
		return ResponseEntity.ok(!isAvailable);
	}

	@PostMapping("/signup/nickname")
	public ResponseEntity<Void> signup(@RequestHeader("Authorization") String authorization,
									   @RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
		try {
//			ResponseEntity<UUID> response = authServiceClient.extractUUIDFromToken(authorization);
//			UUID userId = response.getBody();
			UUID userId = authServiceClient.extractUUID(UUID.fromString(authorization)).getBody();

			userService.signup(userId, userSignUpRequestDTO);
//			authServiceClient.signup(authorization);
		} catch (FeignException e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> getMainPage(@RequestHeader("Authorization") String token) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
			MainPageResponseDTO mainPageResponseDTO = pageService.getMainPage(userId);
			resultMap.put("mainPageResponseDTO", mainPageResponseDTO);
			return ResponseEntity.status(HttpStatus.OK).body(resultMap);

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
		}
	}
}
