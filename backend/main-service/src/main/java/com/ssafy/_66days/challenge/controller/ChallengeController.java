package com.ssafy._66days.challenge.controller;

import com.ssafy._66days.challenge.model.dto.requestDTO.GroupChallengeRequestDTO;
import com.ssafy._66days.challenge.model.dto.requestDTO.ManageApplicationRequestDTO;
import com.ssafy._66days.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.*;
import com.ssafy._66days.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.challenge.model.service.MyChallengeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final GroupChallengeService groupChallengeService;
    private final MyChallengeService myChallengeService;
    private final String userIdStr = "a817d372-ee0d-11ed-a26b-0242ac110003";
    private final UUID userId = UUID.fromString(userIdStr);

    @Autowired
    public ChallengeController(
            GroupChallengeService groupChallengeService,
            MyChallengeService myChallengeService
    ) {
        this.groupChallengeService = groupChallengeService;
        this.myChallengeService = myChallengeService;
    }

    // 개인 챌린지 시작 가능 목록 반환
    @GetMapping("/startMyChallenge")
    @ApiOperation(value = "가능한 개인 챌린지 목록 반환 API", notes = "챌린지 만들기 클릭 시 나오는 챌린지 목록")
    public ResponseEntity<Map<String, Object>> getAvailableMyChallengeList(
//            @RequestHeader(name = "Authorization") String accessToken
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            List<AvailableMyChallengeResponseDTO> availableMyChallengeResponseDTOList = myChallengeService.getAvailableMyChallengeList(userId);
            resultMap.put("availableMyChallengeResponseDTOList", availableMyChallengeResponseDTOList);
            return ResponseEntity.ok().body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 개인 챌린지 생성
    // ? 5개 챌린지를 참여중이거나 동일한 챌린지 참여중이라면 생성 불가
    @PostMapping("/createMyChallenge")
    @ApiOperation(value = "개인 챌린지 생성 API", notes = "개인 챌린지 생성")
    public ResponseEntity<Map<String, Object>> createMyChallenge(
//            @RequestHeader(name = "Authorization") String accessToken
            @RequestBody MyChallengeRequestDTO myChallengeRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

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
            // @RequestHeader(name = "Authorization") String accessToken
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            List<MyChallengeResponseDTO> MyChallengeDTOList = myChallengeService.getMyChallenges(userId);
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
            // @RequestHeader(name = "Authorization") String accessToken
            @PathVariable("my_challenge_id") Long MyChallengeId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            MyChallengeDetailResponseDTO myChallengeDetailResponseDTO = myChallengeService.getMyChallengeDetail(userId, MyChallengeId);
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
            // @RequestHeader(name = "Authorization") String accessToken
            @PathVariable("group_id") Long groupId
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            List<AvailableGroupChallengeResponseDTO> availableGroupChallengeResponseDTOList = groupChallengeService.getAvailableGroupChallengeList(userId, groupId);
            resultMap.put("availableGroupChallengeResponseDTOList", availableGroupChallengeResponseDTOList);
            return ResponseEntity.ok().body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 생성(그룹장이란 매니저만 가능)
    @PostMapping("/group/{group_id}")
    @ApiOperation(value = "그룹 챌린지 생성 API", notes = "그룹장, 매니저만 생성가능, 시작 날짜에 동일 챌린지 진행 중 시 생성 불가, 최대 30일 이내 시작 가능")
    public ResponseEntity<Map<String, Object>> createGroupChallenge(
            // @RequestHeader(name = "Authorization") String accessToken
            @PathVariable("group_id") Long groupId,
            @RequestBody GroupChallengeRequestDTO groupChallengeRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            boolean isCreated = groupChallengeService.createGroupChallenge(userId, groupId, groupChallengeRequestDTO);
            resultMap.put("isCreated", isCreated);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isCreated", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // 그룹 챌린지 가입 신청
    // ? 관련 DB가 없음
    // ? 5개 챌린지를 참여중이거나 동일한 챌린지 참여중이라면 신청 불가
    @PostMapping("/{group_challenge_id}")
    @ApiOperation(value="그룹 챌린지 가입 신청 API", notes="그룹 참여자가 시작하지 않은 챌린지에 가입 신청을 할 수 있다")
    public ResponseEntity<Map<String, Object>> challengeApplication(
            // @RequestHeader(name = "Authorization") String accessToken

    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            return ResponseEntity.status(HttpStatus.OK).body(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    // 그룹장 혹은 매니저 자신이 만든 챌린지 가입 신청 승인 or 거절
    // ? 관련 DB가 없음
    @PatchMapping("/group/{group_id}/group_challenge/{group_challenge_id}")
    @ApiOperation(value="챌린지 가입 승인 or 거절 API", notes="챌린지를 생성한 매니저 혹은 그룹장이 승인 or 거절 할 수 있다")
    public ResponseEntity<Map<String, Object>> manageSubscriptionApplication(
            // @RequestHeader(name = "Authorization") String accessToken
            @PathVariable("group_id") Long groupId,
            @PathVariable("group_challenge_id") Long groupChallengeId,
            @RequestBody ManageApplicationRequestDTO manageApplicationRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    // ? 그룹 챌린지 목록(그룹 들어갔을때) 반환 -->  페이지API로 대체
    @GetMapping("/{group_id}")
    @ApiOperation(value="그룹페이지의 챌린지 목록 반환 API", notes="그룹 페이지 접근 시 해당 그룹 참여자라면 진행중, 예약 챌린지 목록 반환")
    public ResponseEntity<Map<String, Object>> getGroupChallenges(
            // @RequestHeader(name = "Authorization") String accessToken

            @PathVariable("group_id") Long groupId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
            // AuthenticateUtil authenticateUtil = new AuthenticateUtil();
            // UUID userId = authenticateUtil.getUserId(accessToken);

            List<GroupChallengeResponseDTO> groupChallengeResponseDTOList = groupChallengeService.getGroupChallenges(userId, groupId);
            resultMap.put("groupChallengeResponseDTOList", groupChallengeResponseDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    // 그룹 챌린지 상세 페이지 / 스트릭, 챌린지원과 당일 챌린지 달성여부
    // ? 그룹 챌린지 찍기 mongoDB쪽이라 유보
    // ? 개인 스트릭 찍기 mongoDB쪽이라 유보
    // ? 챌린지 끝났을 때 챌린지 상태값, 종료일 update
}
