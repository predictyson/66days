package com.ssafy._66days.challenge.controller;

import com.ssafy._66days.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.challenge.model.dto.responseDTO.AvailableMyChallengeResponseDTO;
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

    // 개인 챌린지 시작 가능 목록
    @GetMapping("/")
    @ApiOperation(value = "가능한 개인 챌린지 목록 반환 API", notes = "챌린지 만들기 클릭 시 나오는 챌린지 목록")
    public ResponseEntity<Map<String, Object>> getAvailableMyChallengeList(
//            @RequestHeader(name = "Authorization") String accessToken
            @PathVariable("group_id_list") List<Long> groupIdList
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // auth서버로 인증 요청
//            AuthenticateUtil authenticateUtil = new AuthenticateUtil();
//            UUID userId = authenticateUtil.getUserId(accessToken);

            List<AvailableMyChallengeResponseDTO> availableMyChallengeResponseDTOs = myChallengeService.getAvailableMyChallengeList(userId, groupIdList);
            resultMap.put("availableMyChallengeResponseDTOs", availableMyChallengeResponseDTOs);
            return ResponseEntity.ok().body(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    // 개인 챌린지 생성
    @PostMapping("/createMyChallenge")
    @ApiOperation(value="개인 챌린지 생성 API", notes = "개인 챌린지 생성")
    public ResponseEntity<Map<String, Object>> createMyChallenge(
//            @RequestHeader(name = "Authorization") String accessToken
            @RequestBody MyChallengeRequestDTO myChallengeRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // auth서버로 인증 요청
//            AuthenticateUtil authenticateUtil = new AuthenticateUtil();
//            UUID userId = authenticateUtil.getUserId(accessToken);

            boolean isSuccess = myChallengeService.createMyChallenge(userId, myChallengeRequestDTO);
            resultMap.put("isSuccess", isSuccess);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    // 개인 챌린지 목록(개인 그룹 들어갔을때) 반환
    // 개인 챌린지 상세 페이지 / 스트릭, 챌린지 히스토리 반환
    // 개인 스트릭 찍기

    // 그룹 챌린지 생성(그룹장이란 매니저만 가능)
    // 그룹 챌린지 가입 신청
    // 그룹 챌린지 가입 신청 허용 or 거부
    // 그룹 챌린지 목록(그룹 들어갔을 때) 반환
    // 그룹 챌린지 상세 페이지 / 스트릭, 챌린지원과 당일 챌린지 달성여부
    // 그룹 챌린지 찍기
}
