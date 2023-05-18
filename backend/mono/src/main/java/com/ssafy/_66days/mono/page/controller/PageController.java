package com.ssafy._66days.mono.page.controller;

import com.ssafy._66days.mono.article.model.service.ArticleService;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.GroupChallengeForGroupIntroPageResDTO;
import com.ssafy._66days.mono.challenge.model.dto.responseDTO.GroupChallengeResponseDTO;
import com.ssafy._66days.mono.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.service.GroupService;
import com.ssafy._66days.mono.user.model.dto.UserDetailDTO;
import com.ssafy._66days.mono.user.model.dto.UserManageDTO;
import com.ssafy._66days.mono.user.model.service.JwtService;
import com.ssafy._66days.mono.user.model.service.UserService;
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
    private final JwtService jwtService;

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

        jwtService.validateToken(token);
        UUID userId = jwtService.getUserId(token);
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

        jwtService.validateToken(token);
        UUID userId = jwtService.getUserId(token);
        log.info("Group Page, USER ID : {}", userId);

        String groupName = groupService.getGroupName(groupId);
        List<GroupChallengeResponseDTO> groupChallenges = groupChallengeService.getGroupChallenges(userId, groupId);
        List<Object> articleList = articleService.getArticleList(userId, groupId, 0);

        resultMap.put("group-name", groupName);
        resultMap.put("badges", new ArrayList<>());
        resultMap.put("challenges", groupChallenges);
        resultMap.put("articles", articleList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹 인트로 페이지", notes = "그룹 인트로 페이지")
    @GetMapping("/groups/intro/{group_id}")
    public ResponseEntity<Map<String, Object>> getGroupIntroPage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ) {
        Map<String, Object> resultMap = new HashMap<>();

        jwtService.validateToken(token);
        UUID userId = jwtService.getUserId(token);
        log.info("Group Intro Page, USER ID : {}", userId);

        Group group = groupService.findGroupById(groupId);
        if (group == null) {
            throw new NoSuchElementException("존재하지 않는 그룹입니다.");
        }

        resultMap.put("group-name", group.getGroupName());
        resultMap.put("group-image-path", group.getImagePath());

        long successfulGroupChallengeCount = groupChallengeService.getCountOfSuccessfulGroupChllaenges(group);
        long activatedGroupChallengeCount =  groupChallengeService.getCountOfActivatedGroupChllaenges(group);

        resultMap.put("completed", successfulGroupChallengeCount);
        resultMap.put("progress", activatedGroupChallengeCount);
        List<GroupChallengeForGroupIntroPageResDTO> groupChallengeForGroupIntroPageResDTO
                =  groupChallengeService.getGroupChallengeListByIdAndStates(group);
        List<UserManageDTO> groupMemberList = groupService.getGroupMembers(groupId);
        int groupMemberCount = Math.min(8, groupMemberList.size());
        List<UserManageDTO> groupMemberListTop8 = new ArrayList<>(groupMemberList.subList(0, groupMemberCount));

        resultMap.put("challenge-list", groupChallengeForGroupIntroPageResDTO);
        resultMap.put("group-members-count", groupMemberList.size());
        resultMap.put("group-members", groupMemberListTop8);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}