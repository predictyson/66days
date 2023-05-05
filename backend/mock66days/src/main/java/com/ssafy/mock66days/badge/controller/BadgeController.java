package com.ssafy.mock66days.badge.controller;

import com.ssafy.mock66days.article.model.dto.ArticleRequestDTO;
import com.ssafy.mock66days.article.model.dto.ArticleResponseDTO;
import com.ssafy.mock66days.article.model.dto.CommentDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/badge")
@Api("Badge API")
public class BadgeController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";

    @ApiOperation(value = "개인 뱃지 리스트 API", notes = "개인 뱃지 리스트")
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getMyBadges(){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        BadgeResponseDTO badge1 = BadgeResponseDTO.builder()
                .image(IMAGE)
                .challengeName("알고리즘 66일 챌린지")
                .startDate(LocalDate.now().minusDays(66))
                .endDate(LocalDate.now())
                .category("알고리즘")
                .status(true)
                .build();

        BadgeResponseDTO badge2 = BadgeResponseDTO.builder()
                .image(IMAGE)
                .challengeName("개발개발개발")
                .startDate(LocalDate.now().minusDays(77))
                .endDate(LocalDate.now().minusDays(33))
                .category("개발서적")
                .status(false)
                .build();

        List<BadgeResponseDTO> badges = new ArrayList<>();
        badges.add(badge1);
        badges.add(badge2);
        badges.add(badge1);
        badges.add(badge2);

        resultMap.put("badge-list",badges);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
    @ApiOperation(value = "그룹 뱃지 리스트 API", notes = "그룹 뱃지 리스트")
    @GetMapping("/list/{group_id}")
    public ResponseEntity<Map<String, Object>> getGroupBadges(
            @PathVariable("group_id") @ApiParam(required = true) int groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        BadgeResponseDTO badge1 = BadgeResponseDTO.builder()
                .image(IMAGE)
                .challengeName("알고리즘 66일 챌린지")
                .startDate(LocalDate.now().minusDays(66))
                .endDate(LocalDate.now())
                .category("알고리즘")
                .status(true)
                .build();

        BadgeResponseDTO badge2 = BadgeResponseDTO.builder()
                .image(IMAGE)
                .challengeName("개발개발개발")
                .startDate(LocalDate.now().minusDays(77))
                .endDate(LocalDate.now().minusDays(33))
                .category("개발서적")
                .status(false)
                .build();

        List<BadgeResponseDTO> badges = new ArrayList<>();
        badges.add(badge1);
        badges.add(badge2);
        badges.add(badge1);
        badges.add(badge2);

        resultMap.put("badge-list",badges);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


}