package com.ssafy._66days.mainservice.rank.controller;

import com.ssafy._66days.mainservice.rank.model.entity.BadgeRank;
import com.ssafy._66days.mainservice.rank.model.entity.ExpRank;
import com.ssafy._66days.mainservice.rank.model.service.RankService;
import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
@Slf4j
public class RankController {

    private RankService rankService;
    private AuthServiceClient authServiceClient;

    @ApiOperation(value = "로그인한 유저의 뱃지 랭킹", notes = "토큰 -> userId -> 최근 랭킹 검색")
    @GetMapping("/badge")
    public ResponseEntity<BadgeRank> getBadgeRankByUserId(@RequestHeader(value = "Authorization") String token) {
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        BadgeRank userRankByBadge = rankService.findBadgeRankByUserId(userId);
        return ResponseEntity.ok(userRankByBadge);
    }

    @ApiOperation(value = "로그인한 유저의 경험치 랭킹", notes = "토큰 -> userId -> 최근 랭킹 검색")
    @GetMapping("/exp")
    public ResponseEntity<ExpRank> getExpRankByUserId(@RequestHeader(value = "Authorization") String token) {
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        ExpRank userRankByExp = rankService.findExpRankByUserId(userId);
        return ResponseEntity.ok(userRankByExp);
    }

    @ApiOperation(value = "탑 3 유저 랭킹(뱃지)", notes = "최근 탑 3 유저 뱃지 랭킹")
    @GetMapping("/badge/top3")
    public ResponseEntity<List<BadgeRank>> getTop3BadgeRanks(@RequestHeader(value = "Authorization") String token) {
        List<BadgeRank> top3BadgeRanks = rankService.findTop3BadgeRanks();
        if (top3BadgeRanks.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(top3BadgeRanks);
        }
    }

    @ApiOperation(value = "탑 3 유저 랭킹(경험치)", notes = "최근 탑 3 유저 경험치 랭킹")
    @GetMapping("/exp/top3")
    public ResponseEntity<List<ExpRank>> getTop3ExpRanks(@RequestHeader(value = "Authorization") String token) {
        List<ExpRank> top3ExpRanks = rankService.findTop3ExpRanks();
        if (top3ExpRanks.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(top3ExpRanks);
        }
    }
}
