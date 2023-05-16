package com.ssafy._66days.mainservice.group.controller;

import com.ssafy._66days.mainservice.group.model.dto.GroupCreateDTO;
import com.ssafy._66days.mainservice.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.mainservice.group.model.service.GroupService;
import com.ssafy._66days.mainservice.user.feign.AuthServiceClient;
import com.ssafy._66days.mainservice.user.model.dto.UserManageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/group")
@Api("GROUP API")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private final GroupService groupService;
    private final AuthServiceClient authServiceClient;
    @ApiOperation(value = "검색 API", notes = "search 누를 시 나오는 화면")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchGroup(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam String searchContent, @RequestParam String filterBy
    ) {
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group search, USER ID : {}", userId);

        List<GroupSearchPageResponseDTO> groupList = groupService.searchGroup(searchContent, filterBy);

        resultMap.put("group-list", groupList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "그룹원 관리")
    @GetMapping("/{group_id}/manage/members")
    public ResponseEntity<Map<String, Object>> getGroupManage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ){
        Map<String, Object> resultMap = new HashMap<>();

        System.out.println(token);

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group manage/members, USER ID : {}", userId);

        //is User a group Owner?
        boolean isOwner = groupService.isUserGroupOwner(userId,groupId);
        if(!isOwner) {
            resultMap.put(RESULT, "유저가 권한을 가지고 있지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.FORBIDDEN);
        }

        List<UserManageDTO> memberList = groupService.getGroupMembers(groupId);
        resultMap.put("member-list", memberList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "대기중인 요청")
    @GetMapping("/{group_id}/manage/apply")
    public ResponseEntity<Map<String, Object>> getGroupApply(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ){
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group manage/apply, USER ID : {}", userId);

        //is User a group Owner?
        boolean isOwner = groupService.isUserGroupOwner(userId,groupId);
        if(!isOwner) {
            resultMap.put(RESULT, "유저가 권한을 가지고 있지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.FORBIDDEN);
        }

        List<UserManageDTO> memberList = groupService.getGroupApplyList(groupId);
        resultMap.put("apply-list", memberList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "대기중인 요청 API", notes = "대기중인 요청에서 수락/거절 누를 시")
    @PostMapping("/{group_id}/manage/apply/{status}")
    public ResponseEntity<Map<String, Object>> permitApply(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group manage/permit, USER ID : {}", userId);

        //is User a group Owner?
        boolean isOwner = groupService.isUserGroupOwner(userId,groupId);
        if(!isOwner) {
            resultMap.put(RESULT, "유저가 권한을 가지고 있지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.FORBIDDEN);
        }

        try {
            groupService.setGroupApplyState(groupId,state, userName);
        } catch (Exception e){
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 요청 API", notes = "그룹원 관리에서 매니저 지정/해임, 강퇴")
    @PatchMapping("/{group_id}/manage/members/{status}")
    public ResponseEntity<Map<String, Object>> memberManage(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group manage/members/status, USER ID : {}", userId);

        //is User a group Owner?
        boolean isOwner = groupService.isUserGroupOwner(userId,groupId);
        if(!isOwner) {
            resultMap.put(RESULT, "유저가 권한을 가지고 있지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.FORBIDDEN);
        }

        try {
            groupService.setGroupMemberState(groupId, state, userName);
        } catch (Exception e){
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹신청/신청취소 API", notes = "유저가 그룹에 신청/신청취소")
    @PutMapping("/{group_id}/apply/{status}")
    public ResponseEntity<Map<String, Object>> groupApply(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state
    ){
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group apply/status, USER ID : {}", userId);

        try {
            groupService.applyGroup(groupId, state, userId);
        } catch (Exception e){
            resultMap.put(RESULT, e.getMessage());
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹 생성 API", notes = "유저가 그룹을 생성")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createGroup(
            @RequestHeader(value = "Authorization") String token,
            @RequestPart(value="groupContent") GroupCreateDTO groupCreateDTO,
            @RequestPart(value="image") @ApiParam(required = true) MultipartFile groupImage
            ){
        Map<String, Object> resultMap = new HashMap<>();

        //token validation
        UUID userId = authServiceClient.extractUUID(UUID.fromString(token)).getBody();
        log.info("Group create, USER ID : {}", userId);

        try {
            groupService.createGroup(groupCreateDTO, groupImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}

