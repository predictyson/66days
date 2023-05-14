package com.ssafy._66days.mainservice.group.controller;

import com.ssafy._66days.mainservice.group.model.dto.GroupCreateDTO;
import com.ssafy._66days.mainservice.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.mainservice.group.model.service.GroupService;
import com.ssafy._66days.mainservice.user.model.dto.UserManageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
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
public class GroupController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";
    private final GroupService groupService;
    @ApiOperation(value = "검색 API", notes = "search 누를 시 나오는 화면")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchGroup(
//            @RequestHeader(value = "Authorization") String token
            @RequestParam String searchContent, @RequestParam String filterBy
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<GroupSearchPageResponseDTO> groupList = groupService.searchGroup(searchContent, filterBy);

        resultMap.put("group-list", groupList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "그룹원 관리")
    @GetMapping("/{group_id}/manage/members")
    public ResponseEntity<Map<String, Object>> getGroupManage(
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<UserManageDTO> memberList = groupService.getGroupMembers(groupId);
        resultMap.put("member-list", memberList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "대기중인 요청")
    @GetMapping("/{group_id}/manage/apply")
    public ResponseEntity<Map<String, Object>> getGroupApply(
            @PathVariable("group_id") @ApiParam(required = true) Long groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<UserManageDTO> memberList = groupService.getGroupApplyList(groupId);
        resultMap.put("apply-list", memberList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "대기중인 요청 API", notes = "대기중인 요청에서 수락/거절 누를 시")
    @PostMapping("/{group_id}/manage/apply/{status}")
    public ResponseEntity<Map<String, Object>> permitApply(
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        groupService.setGroupApplyState(groupId,state, userName);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 요청 API", notes = "그룹원 관리에서 매니저 지정/해임, 강퇴")
    @PatchMapping("/{group_id}/manage/members/{status}")
    public ResponseEntity<Map<String, Object>> memberManage(
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        groupService.setGroupMemberState(groupId, state, userName);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹신청/신청취소 API", notes = "유저가 그룹에 신청/신청취소")
    @PutMapping("/{group_id}/apply/{status}")
    public ResponseEntity<Map<String, Object>> groupApply(
            @PathVariable("group_id") @ApiParam(required = true) Long groupId,
            @PathVariable("status") @ApiParam(required = true) String state
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        UUID userId = UUID.randomUUID();
        groupService.applyGroup(groupId, state, userId);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹 생성 API", notes = "유저가 그룹을 생성")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createGroup(
            @RequestPart(value="groupContent") GroupCreateDTO groupCreateDTO, @RequestPart(value="image") @ApiParam(required = true) MultipartFile groupImage
            ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            groupService.createGroup(groupCreateDTO, groupImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}