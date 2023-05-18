//package com.ssafy._66days.mono.badge.controller;
//
//import com.ssafy._66days.mono.badge.model.dto.ResponseDTO.BadgeDetailResponseDTO;
//import com.ssafy._66days.mono.badge.model.dto.ResponseDTO.BadgeListResponseDTO;
//import com.ssafy._66days.mono.badge.model.service.BadgeService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/badge")
//public class BadgeController {
//    private BadgeService badgeService;
//
//    @Autowired
//    public BadgeController(BadgeService badgeService) {
//        this.badgeService = badgeService;
//    }
//
//    @GetMapping("/group/{gourp_id}")
//    @ApiOperation(value = "그룹 전체 뱃지 조회 API", notes = "그룹의 전체 뱃지를 조회")
//    public ResponseEntity<Map<String, Object>> getGroupBadges(
//            @PathVariable("gourp_id") Long groupId
//    ) {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//
//        try {
//            List<BadgeListResponseDTO> badgeList = badgeService.getGroupBadgeList(groupId);
//            resultMap.put("groupBadgeList", badgeList);
//            return ResponseEntity.ok().body(resultMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
//        }
//    }
//
//    @GetMapping("/group/{group_id}/{badge_id}")
//    @ApiOperation(value = "그룹 뱃지 상세 조회 API", notes = "그룹의 특정 뱃지를 조회")
//    public ResponseEntity<Map<String, Object>> getGroupBadgeDetail(
//            @PathVariable("group_id") Long groupId,
//            @PathVariable("badge_id") Long badgeId
//    ) {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//
//        try {
//            List<BadgeDetailResponseDTO> badge = badgeService.getGroupBadge(groupId, badgeId);
//            resultMap.put("groupBadgeDetail", badge);
//            return ResponseEntity.ok().body(resultMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
//        }
//    }
//
//    @GetMapping("/private")
//    @ApiOperation(value = "개인 전체 뱃지 조회 API", notes = "개인의 전체 뱃지를 조회")
//    public ResponseEntity<Map<String, Object>> getPrivateBadges(
//    ) {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//
//        try {
//            List<BadgeListResponseDTO> badgeList = badgeService.getPrivateBadgeList();
//            resultMap.put("privateBadgeList", badgeList);
//            return ResponseEntity.ok().body(resultMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
//        }
//    }
//
//    @GetMapping("/private/{badge_id}")
//    @ApiOperation(value = "개인 뱃지 상세 조회 API", notes = "개인의 특정 뱃지를 조회")
//    public ResponseEntity<Map<String, Object>> getPrivateBadgeDetail(
//            @PathVariable("badge_id") Long badgeId
//
//    ) {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//
//        try {
//            List<BadgeDetailResponseDTO> badge = badgeService.getPrivateBadge(badgeId);
//            resultMap.put("privateBadgeDetail", badge);
//            return ResponseEntity.ok().body(resultMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
//        }
//    }
//
//}
