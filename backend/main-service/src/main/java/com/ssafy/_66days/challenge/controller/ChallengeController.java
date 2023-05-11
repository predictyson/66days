package com.ssafy._66days.challenge.controller;

import com.ssafy._66days.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.challenge.model.service.MyChallengeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {
    private final GroupChallengeService groupChallengeService;
    private final MyChallengeService myChallengeService;

    public ChallengeController(
            GroupChallengeService groupChallengeService,
            MyChallengeService myChallengeService
    ) {
        this.groupChallengeService = groupChallengeService;
        this.myChallengeService = myChallengeService;
    }

    // 개인 챌린지 생성
    public MyChallengeResponseDTO createMyChallenge
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
