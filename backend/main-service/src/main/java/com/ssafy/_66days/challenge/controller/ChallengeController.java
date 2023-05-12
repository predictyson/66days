package com.ssafy._66days.challenge.controller;

import com.ssafy._66days.challenge.model.dto.requestDTO.MyChallengeRequestDTO;
import com.ssafy._66days.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.challenge.model.service.MyChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    // 개인 챌린지 생성
    @PostMapping("/createMyChallenge")
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
