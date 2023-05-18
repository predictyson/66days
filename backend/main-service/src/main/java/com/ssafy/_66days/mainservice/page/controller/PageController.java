package com.ssafy._66days.mainservice.page.controller;

import com.ssafy._66days.mainservice.article.model.dto.responseDto.ArticleResponseDTO;
import com.ssafy._66days.mainservice.article.model.service.ArticleService;
import com.ssafy._66days.mainservice.badge.model.dto.ResponseDTO.BadgeMyPageDTO;
import com.ssafy._66days.mainservice.badge.model.service.BadgeService;
import com.ssafy._66days.mainservice.challenge.model.dto.responseDTO.GroupChallengeForGroupIntroPageResDTO;
import com.ssafy._66days.mainservice.challenge.model.dto.responseDTO.GroupChallengeResponseDTO;
import com.ssafy._66days.mainservice.challenge.model.dto.responseDTO.MyChallengeResponseDTO;
import com.ssafy._66days.mainservice.challenge.model.service.GroupChallengeService;
import com.ssafy._66days.mainservice.challenge.model.service.MyChallengeService;
import com.ssafy._66days.mainservice.group.model.dto.GroupAchievementResponseDTO;
import com.ssafy._66days.mainservice.group.model.dto.GroupMyPageResponseDTO;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupMember;
import com.ssafy._66days.mainservice.group.model.service.GroupService;
import com.ssafy._66days.mainservice.page.model.dto.MainPageResponseDTO;
import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailDTO;
<<<<<<< HEAD
import com.ssafy._66days.mainservice.user.model.dto.UserManageDTO;
=======
import com.ssafy._66days.mainservice.user.model.dto.UserDetailResponseDTO;
>>>>>>> 719ef97 (be/feat: 메인 페이지 랭킹 제외 완성)
import com.ssafy._66days.mainservice.user.model.service.UserService;
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
    private final MyChallengeService myChallengeService;
    private final BadgeService badgeService;

    private final AuthServiceClient authServiceClient;

    @ApiOperation(value = "홈 화면", notes = "로그인 후 연결 되는 첫 페이지")
    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> getMainPage(
            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group Page, USER ID : {}", userId);

        try {
            MainPageResponseDTO mainPage = userService.getMainPage(userId);
            resultMap.put("mainPage", mainPage);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
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
            resultMap.put("userInfo", userDetailDTO);
        } catch (Exception e) {
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
        }

//        resultMap.put("member-info", member);

        List<BadgeMyPageDTO> badgeMyPageDTOList = badgeService.getMyPageBadgeList(userId);
        resultMap.put("badges", badgeMyPageDTOList);

        resultMap.put("streak", new ArrayList<>());

        try {
            List<MyChallengeResponseDTO> challengeList = myChallengeService.getMyChallenges(userId,"SUCCESSFUL");
            resultMap.put("challenge", new ArrayList<>());
        } catch (Exception e) {
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
        }

        try {
            List<GroupMyPageResponseDTO> groupList = groupService.findAllGroups(userId);
            resultMap.put("groupList", groupList);
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
        List<Object> articleList = articleService.getArticleList(userId, groupId, 0);

        resultMap.put("group-name", groupName);
        resultMap.put("achievements", groupAchievements);
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

        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
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
