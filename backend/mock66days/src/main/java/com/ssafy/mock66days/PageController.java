package com.ssafy.mock66days;

import com.ssafy.mock66days.challenge.model.dto.ChallengeMainPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupMainPageResponseDTO;
import com.ssafy.mock66days.member.model.dto.MemberMainPageResponseDTO;
import com.ssafy.mock66days.member.model.dto.UserUpdateParamDTO;
import com.ssafy.mock66days.rank.model.dto.RankMainPageResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Api("PAGE API")
public class PageController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    @ApiOperation(value = "홈 화면", notes = "로그인 후 연결 되는 첫 페이지")
    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> getMainPage(
//            @RequestHeader(value = "Authorization") @ApiParam(required = true) String token
            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        MemberMainPageResponseDTO member = new MemberMainPageResponseDTO();
        member.of("bronze", "moongchi@ssafy.com", 1500, 32000, "/image/카피바라.jpg", "카피바라");
        resultMap.put("member-info", member);

        ChallengeMainPageResponseDTO challenge;
        GroupMainPageResponseDTO group;
        RankMainPageResponseDTO rank;



        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
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