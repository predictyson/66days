package com.ssafy.mock66days.controller;

import com.ssafy.mock66days.model.dto.UserUpdateParamDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Api("MEMBER API")
public class MemberController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";

//    @ApiOperation(value = "소셜 회원가입", notes = "소셜 회원의 회원가입을 진행합니다.")
//    @PostMapping("/social")
//    public ResponseEntity<Map<String, String>> socialRegistration(
//            @RequestBody @ApiParam(required = true) UserSocialRegistParamDTO userDTO
//    ) {
//
//        Map<String, String> resultMap = new HashMap<String, String>();
//
//        resultMap.put(RESULT, SUCCESS);
//        return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
//    }

    @ApiOperation(value = "닉네임 중복 확인", notes = "닉네임이 중복되는 지 여부를 확인해줍니다.")
    @GetMapping("/nickname")
    public ResponseEntity<Map<String, String>> nicknameDuplicateCheck(
            @RequestParam(value = "nickname") @ApiParam(required = true) String nickname
    ) {

        Map<String, String> resultMap = new HashMap<String, String>();

        return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 조회", notes = "해당 유저의 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(
//            @RequestHeader(value = "Authorization") @ApiParam(required = true) String token,
    ) throws NumberFormatException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> tempMap = new HashMap<String, Object>();

        tempMap.put("image","image/123.jpg");
        tempMap.put("tier", "bronze");
        tempMap.put("nickname", "뭉치뭉치똥뭉치");
        tempMap.put("email","moongchi@ssafy.com");
        tempMap.put("experience", 1500);
        tempMap.put("point", 32000);
        tempMap.put("badge-list", new String[]{"CS","블로깅","강의"});
        tempMap.put("challenge-graph", null);
        tempMap.put("challenge-list",new Object[]{"알고리즘 객체","개발서적 객체"});
        tempMap.put("group-list",new Object[]{"17반 알고리즘","자율 7반 5팀"});

//        jwtService.validateToken(token);
        resultMap.put(RESULT, SUCCESS);
        resultMap.put("member", tempMap);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "해당 유저의 정보를 수정합니다.")
    @PatchMapping("/me")
    public ResponseEntity<Map<String, Object>> updateUser(
//            @RequestHeader(value = "Authorization") @ApiParam(required = true) String token,
            @RequestBody @ApiParam(required = true) UserUpdateParamDTO userDTO
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

//        jwtService.validateToken(token);
//        long userId = jwtService.getUserId(token);
        resultMap.put(RESULT, SUCCESS);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 진행합니다.")
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(
            @RequestHeader(value = "Authorization") @ApiParam(required = true) String token
    ) {

        Map<String, String> resultMap = new HashMap<String, String>();

//        jwtService.validateToken(token);
        resultMap.put(RESULT, SUCCESS);
        return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
    }
}