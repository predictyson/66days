package com.ssafy._66days.group.controller;

import com.ssafy._66days.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy._66days.group.model.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//        GroupSearchPageResponseDTO group = GroupSearchPageResponseDTO.builder()
//                .ownerImage(IMAGE)
//                .ownerName("뭉치")
//                .name("뭉치뭉치똥뭉치네")
//                .categories(new ArrayList<>(Arrays.asList("알고리즘","강의")))
//                .description("같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요")
//                .memberCounts(33)
//                .maxMemberCounts(66)
//                .animal("카피바라")
//                .build();

        resultMap.put("group-list", groupList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "그룹원 관리")
    @GetMapping("/{group_id}/manage/members")
    public ResponseEntity<Map<String, Object>> getGroupManage(
            @PathVariable("group_id") @ApiParam(required = true) int groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

//        MemberManageDTO member1 = MemberManageDTO.builder()
//                .image(IMAGE)
//                .nickname("@moongchi")
//                .badge(1)
//                .role("owner")
//                .build();


        List<MemberManageDTO> memberList = groupService.getGroupMembers(groupId);
        resultMap.put("member-list", members);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "대기중인 요청")
    @GetMapping("/{group_id}/manage/apply")
    public ResponseEntity<Map<String, Object>> getGroupApply(
            @PathVariable("group_id") @ApiParam(required = true) int groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

//        MemberManageDTO member1 = MemberManageDTO.builder()
//                .image(IMAGE)
//                .nickname("@moongchi")
//                .badge(1)
//                .build();

        List<MemberManageDTO> memberList = groupService.getGroupApplyList(groupId);
        resultMap.put("apply-list", members);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "대기중인 요청 API", notes = "대기중인 요청에서 수락/거절 누를 시")
    @PostMapping("/{group_id}/manage/apply/{status}")
    public ResponseEntity<Map<String, Object>> permitApply(
            @PathVariable("group_id") @ApiParam(required = true) int groupId,
            @PathVariable("status") @ApiParam(required = true) String status,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 요청 API", notes = "그룹원 관리에서 매니저 지정/해임, 강퇴")
    @PatchMapping("/{group_id}/manage/members/{status}")
    public ResponseEntity<Map<String, Object>> memberManage(
            @PathVariable("group_id") @ApiParam(required = true) int groupId,
            @PathVariable("status") @ApiParam(required = true) String status,
            @RequestParam("user_name") @ApiParam(required = true) String userName
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

}