package com.ssafy._66days.mainservice.page.controller;

import com.ssafy._66days.mainservice.article.model.dto.responseDto.ArticleResponseDTO;
import com.ssafy._66days.mainservice.article.model.service.ArticleService;
import com.ssafy._66days.mainservice.challenge.model.dto.responseDTO.GroupChallengeResponseDTO;
import com.ssafy._66days.mainservice.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.mainservice.group.model.repository.GroupAchievementResponseDTO;
import com.ssafy._66days.mainservice.group.model.service.GroupService;
import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailDTO;
import com.ssafy._66days.mainservice.user.model.service.UserService;
import feign.FeignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/page")
@Api("PAGE API")
@RequiredArgsConstructor
@Slf4j
public class PageController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private final ArticleService articleService;
    private final GroupChallengeService groupChallengeService;
    private final UserService userService;
    private final GroupService groupService;
    private final AuthServiceClient authServiceClient;

    @ApiOperation(value = "홈 화면", notes = "로그인 후 연결 되는 첫 페이지")
    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> getMainPage(
//            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

//        resultMap.put("member-info", member);
//        resultMap.put("challenge", cList);
//        resultMap.put("group", gList);
//        resultMap.put("rank", rank);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "마이페이지", notes = "내 프로필 화면")
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMyPage(
            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group Page, USER ID : {}", userId);

        try {
            UserDetailDTO userDetailDTO = userService.findUserById(userId);
            resultMap.put("user-info", userDetailDTO);
        } catch (Exception e) {
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
        }

//        resultMap.put("member-info", member);
        resultMap.put("badges", new ArrayList<>());
        resultMap.put("streak", new ArrayList<>());
        resultMap.put("challenge", new ArrayList<>());

        try {
//            groupService.findAllGroups(userId);
//            resultMap.put("user-info", userDetailDTO);
//        resultMap.put("group", gList);
        } catch (Exception e) {
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
        }



        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹 페이지", notes = "그룹페이지의 첫 화면")
    @GetMapping("/groups/{group_id}")
    public ResponseEntity<Map<String, Object>> getGroupPage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group Page, USER ID : {}", userId);

        String groupName = groupService.getGroupName(groupId);
        List<GroupAchievementResponseDTO> groupAchievements = groupService.getGroupAchievement(userId, groupId);
        List<GroupChallengeResponseDTO> groupChallenges = groupChallengeService.getGroupChallenges(userId, groupId);
        List<ArticleResponseDTO> articleList = articleService.getArticleList(userId, groupId, 0);

        resultMap.put("group-name", groupName);
        resultMap.put("achievements", groupAchievements);
        resultMap.put("challenges", groupChallenges);
        resultMap.put("articles", articleList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}