package com.ssafy.mock66days.challenge.controller;

import com.ssafy.mock66days.article.model.dto.ArticleRequestDTO;
import com.ssafy.mock66days.article.model.dto.ArticleResponseDTO;
import com.ssafy.mock66days.article.model.dto.CommentDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeRequestDTO;
import com.ssafy.mock66days.member.model.dto.MemberChallengeDTO;
import com.ssafy.mock66days.member.model.dto.MemberDTO;
import com.ssafy.mock66days.streak.model.dto.StreakGroupResponseDTO;
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
@RequestMapping("/challenge")
@Api("Challenge API")
public class ChallengeController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";

    @ApiOperation(value = "개인 챌린지 페이지 API", notes = "개인 챌린지 페이지")
    @GetMapping("/{challenge_id}")
    public ResponseEntity<Map<String, Object>> getMyChallenge(){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("explain me page", "explain me page");
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
    @ApiOperation(value = "그룹 챌린지 페이지 API", notes = "그룹 챌린지 페이지")
    @GetMapping("/list/{group_id}")
    public ResponseEntity<Map<String, Object>> getGroupChallenge(
            @PathVariable("group_id") @ApiParam(required = true) int groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("challenge-name","김태원의 Figma");
        resultMap.put("description","피그마 마스터가 되고 싶은 김태원");

        MemberChallengeDTO member1 = MemberChallengeDTO.builder()
                .image(IMAGE)
                .nickname("김태원")
                .status(true)
                .build();

        MemberChallengeDTO member2 = MemberChallengeDTO.builder()
                .image(IMAGE)
                .nickname("뭉치")
                .status(false)
                .build();

        MemberChallengeDTO member3 = MemberChallengeDTO.builder()
                .image(IMAGE)
                .nickname("해피")
                .status(true)
                .build();

        List<MemberChallengeDTO> mList = new ArrayList<>();
        mList.add(member1);
        mList.add(member2);
        mList.add(member3);
        resultMap.put("members",mList);

        StreakGroupResponseDTO streak1 = StreakGroupResponseDTO.builder()
                .date(LocalDate.now())
                .status("freeze") //true, false, freeze
                .count(4)
                .build();

        StreakGroupResponseDTO streak2 = StreakGroupResponseDTO.builder()
                .date(LocalDate.now())
                .status("true") //true, false, freeze
                .count(0)
                .build();

        StreakGroupResponseDTO streak3 = StreakGroupResponseDTO.builder()
                .date(LocalDate.now())
                .status("false") //true, false, freeze
                .count(0)
                .build();

        List<StreakGroupResponseDTO> sList = new ArrayList<>();
        sList.add(streak1);
        sList.add(streak2);
        sList.add(streak3);
        resultMap.put("streak",sList);

        resultMap.put("chat","TBD");
        resultMap.put("remain-streak", 0);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
    @ApiOperation(value = "챌린지 추가 페이지 API", notes = "챌린지 추가")
    @GetMapping("/new")
    public ResponseEntity<Map<String, Object>> addChallenge(
            @RequestBody ChallengeRequestDTO challengeRequestDTO
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("challenge-id", 1);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


}