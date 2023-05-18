package com.ssafy._66days.mono.challenge.controller;

import com.ssafy._66days.mono.challenge.model.dto.requestDTO.GroupChallengeRequestDTO;
import com.ssafy._66days.mono.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.*;
import com.ssafy._66days.mono.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.mono.challenge.model.service.MyChallengeService;
import com.ssafy._66days.mono.user.model.service.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {
    private final GroupChallengeService groupChallengeService;
    private final MyChallengeService myChallengeService;
    private final JwtService jwtService;

    // 개인 챌린지 시작 가능 목록 반환
    @GetMapping("/startMyChallenge")
    @ApiOperation(value = "가능한 개인 챌린지 목록 반환 API", notes = "챌린지 만들기 클릭 시 나오는 챌린지 목록")
    public ResponseEntity<Map<String, Object>> getAvailableMyChallengeList(
            @RequestHeader(value = "Authorization") String token
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            List<AvailableMyChallengeResponseDTO> availableMyChallengeResponseDTOList = myChallengeService.getAvailableMyChallengeList(userId);
            resultMap.put("availableMyChallengeResponseDTOList", availableMyChallengeResponseDTOList);
            return ResponseEntity.ok().body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 개인 챌린지 생성
    // 5개 챌린지를 참여중이거나 동일한 챌린지 참여중이라면 생성 불가
    @PostMapping("/createMyChallenge")
    @ApiOperation(value = "개인 챌린지 생성 API", notes = "개인 챌린지 생성")
    public ResponseEntity<Map<String, Object>> createMyChallenge(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MyChallengeRequestDTO myChallengeRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            boolean isSuccess = myChallengeService.createMyChallenge(userId, myChallengeRequestDTO);
            resultMap.put("isSuccess", isSuccess);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // ? 개인 챌린지 목록(개인 그룹 들어갔을때) 반환 -->  페이지API로 대체
    @GetMapping("/myChallenges")
    @ApiOperation(value = "개인 챌린지 목록 반환 API", notes = "개인 그룹 페이지에 노출되는 개인 챌린지 목록")
    public ResponseEntity<Map<String, Object>> getMyChallenges(
            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            List<MyChallengeResponseDTO> MyChallengeDTOList = myChallengeService.getMyChallenges(userId, "ACTIVATED");
            resultMap.put("MyChallengeDTOList", MyChallengeDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 개인 챌린지 상세 페이지 / 스트릭, 챌린지 히스토리 반환
    @GetMapping("/my/{my_challenge_id}")
    @ApiOperation(value = "개인 챌린지 상세 페이지 API", notes = "개인 챌린지 상세눌렀을 때 오른쪽에 보이는 개인 챌린지 히스토리 정보와 챌린지 이름, 설명")
    public ResponseEntity<Map<String, Object>> getMyChallengeDetail(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("my_challenge_id") Long myChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            MyChallengeDetailResponseDTO myChallengeDetailResponseDTO = myChallengeService.getMyChallengeDetail(userId, myChallengeId);
            resultMap.put("myChallengeHistoryDTO", myChallengeDetailResponseDTO);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 생성 가능한 챌린지 반환
    @GetMapping("/startGroupChallenge/{group_id}")
    @ApiOperation(value = "그룹 챌린지 만들기 API", notes = "챌린지 만들기 클릭 시 챌린지 리스트 반환")
    public ResponseEntity<Map<String, Object>> getAvailableGroupChallengeList(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") Long groupId
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            List<AvailableGroupChallengeResponseDTO> availableGroupChallengeResponseDTOList = groupChallengeService.getAvailableGroupChallengeList(userId, groupId);
            resultMap.put("availableGroupChallengeResponseDTOList", availableGroupChallengeResponseDTOList);
            return ResponseEntity.ok().body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 생성(그룹장이란 매니저만 가능)
    @PostMapping("/group/{group_id}")
    @ApiOperation(value = "그룹 챌린지 생성 API", notes = "그룹장, 매니저만 생성가능, 시작 날짜에 동일 챌린지 진행 중 시 생성 불가, 최대 30일 이내 시작 가능")
    public ResponseEntity<Map<String, Object>> createGroupChallenge(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") Long groupId,
            @RequestBody GroupChallengeRequestDTO groupChallengeRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            boolean isCreated = groupChallengeService.createGroupChallenge(userId, groupId, groupChallengeRequestDTO);
            resultMap.put("isCreated", isCreated);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 가입 신청
    @PostMapping("/{group_challenge_id}")
    @ApiOperation(value = "그룹 챌린지 가입 신청 API", notes = "그룹 참여자가 시작하지 않은 챌린지에 가입 신청을 할 수 있다")
    public ResponseEntity<Map<String, Object>> challengeApplication(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_challenge_id") Long groupChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);
            boolean isApplied = groupChallengeService.challengeApplication(userId, groupChallengeId);
            resultMap.put("isApplied", isApplied);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // ? 챌린지 신청자 목록 반환
    // ! 챌린지 신청자 목록 반환
    @GetMapping("/group_challenge/{group_challenge_id}/application_list")
    @ApiOperation(value = "그룹 챌린지 신청자 리스트 API", notes = "그룹 챌린지를 참여 신청한 사람들을 관리할 수 있는 라스트")
    public ResponseEntity<Map<String, Object>> getChallengeApplicationList(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_challenge_id") Long groupChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);
            List<ApplicationListResponseDTO> ApplicationListResponseDTOs = groupChallengeService.getChallengeApplicationList(userId, groupChallengeId);
            resultMap.put("ApplicationListResponseDTOs", ApplicationListResponseDTOs);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    // 그룹장 혹은 매니저 자신이 만든 챌린지 가입 신청 승인 or 거절
    // 참가인원이 맥스 인원을 넘기면 안된다

    @PatchMapping("/group_challenge/{group_challenge_id}/{nickname}/{state}")
    @ApiOperation(value = "챌린지 가입 승인 or 거절 API", notes = "챌린지를 생성한 매니저 혹은 그룹장이 승인 or 거절 할 수 있다")
    public ResponseEntity<Map<String, Object>> manageSubscriptionApplication(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_challenge_id") Long groupChallengeId,
            @PathVariable("nickname") String nickname,
            @PathVariable("state") String state
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            boolean isManaged = groupChallengeService.manageSubscriptionApplication(userId, groupChallengeId, nickname, state);
            resultMap.put("isManaged", isManaged);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isManaged", false);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }


    // ? 그룹 챌린지 목록(그룹 들어갔을때) 반환 -->  페이지API로 대체
    @GetMapping("/{group_id}")
    @ApiOperation(value = "그룹페이지의 챌린지 목록 반환 API", notes = "그룹 페이지 접근 시 해당 그룹 참여자라면 진행중, 예약 챌린지 목록 반환")
    public ResponseEntity<Map<String, Object>> getGroupChallenges(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") Long groupId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            List<GroupChallengeResponseDTO> groupChallengeResponseDTOList = groupChallengeService.getGroupChallenges(userId, groupId);
            resultMap.put("groupChallengeResponseDTOList", groupChallengeResponseDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 상세 페이지
    // 챌린지 이미지경로, 챌린지 이름, 챌린지 설명, 구성원 리스트
    @GetMapping("/group/{group_id}/group_challenge/{group_challenge_id}")
    @ApiOperation(value = "그룹 챌린지 상세 페이지 API", notes = "그룹 챌린지 ")
    public ResponseEntity<Map<String, Object>> getGroupChallengeDetail(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") Long groupId,
            @PathVariable("group_challenge_id") Long groupChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            GroupChallengeDetailResponseDTO groupChallengeDetailResponseDTO = groupChallengeService.getGroupChallengeDetail(userId, groupId, groupChallengeId);
            resultMap.put("groupChallengeDetailResponseDTO", groupChallengeDetailResponseDTO);
            return ResponseEntity.ok().body(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 개인 스트릭 찍기
    @PostMapping("/streak/{my_challenge_id}")
    @ApiOperation(value="개인 챌린지 당일 스트릭 찍기 API", notes="개인 챌린지 상세 화면에서 스트릭을 찍는다")
    public ResponseEntity<Map<String, Object>> checkPrivateStreak(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("my_challenge_id") Long myChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try{
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            boolean isChecked = myChallengeService.checkPrivateStreak(userId, myChallengeId);
            resultMap.put("isChecked", isChecked);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isChecked", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }


    // 그룹 챌린지 찍기
    @PostMapping("/group_streak/{group_challenge_id}/{nickname}")
    @ApiOperation(value="그룹 챌린지에서 개인 당일 스트릭 찍기 API", notes="그룹 챌린지 상세 화면에서 스트릭을 찍는다")
    public ResponseEntity<Map<String, Object>> checkGroupStreak(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_challenge_id") Long groupChallengeId,
            @PathVariable("nickname") String nickname
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try{
            jwtService.validateToken(token);
            UUID userId = jwtService.getUserId(token);
            log.info("Group Page, USER ID : {}", userId);

            boolean isChecked = groupChallengeService.checkGroupStreak(userId, groupChallengeId, nickname);
            resultMap.put("isChecked", isChecked);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // ? 개인 챌린지 스트릭 정보
    // ? 그룹 챌린지 스트릭 정보
    // ? 챌린지 끝났을 때 챌린지 상태값, 종료일 update mongoDB인가?
}
