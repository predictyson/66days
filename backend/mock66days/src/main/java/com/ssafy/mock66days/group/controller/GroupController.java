package com.ssafy.mock66days.group.controller;

import com.ssafy.mock66days.article.model.dto.ArticleGroupPageResponseDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeGroupPageResponseDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeMyPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeGroupPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeMainPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeMyPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupMainPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupMyPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy.mock66days.member.model.dto.Member;
import com.ssafy.mock66days.member.model.dto.MemberMainPageResponseDTO;
import com.ssafy.mock66days.member.model.dto.MemberMyPageResponseDTO;
import com.ssafy.mock66days.rank.model.dto.RankMainPageResponseDTO;
import com.ssafy.mock66days.streak.model.dto.StreakMyPageResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/group")
@Api("GROUP API")
public class GroupController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";
    @ApiOperation(value = "검색 API", notes = "search 누를 시 나오는 화면")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchGroup(
//            @RequestHeader(value = "Authorization") String token
            @RequestParam String searchContent, @RequestParam String filterBy
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        GroupSearchPageResponseDTO group = GroupSearchPageResponseDTO.builder()
                .ownerImage(IMAGE)
                .ownerName("뭉치")
                .name("뭉치뭉치똥뭉치네")
                .categories(new ArrayList<>(Arrays.asList("알고리즘","강의")))
                .description("같이 함께 개발자 준비해요. 프론트엔드 개발자 환영이요")
                .memberCounts(33)
                .maxMemberCounts(66)
                .animal("카피바라")
                .build();

        List<GroupSearchPageResponseDTO> gList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            gList.add(group);
        }
        resultMap.put("group-list", gList);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "그룹관리 API", notes = "그룹 관리 누를 때")
    @GetMapping("/manage")
    public ResponseEntity<Map<String, Object>> getGroupManage(){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        MemberMyPageResponseDTO member = MemberMyPageResponseDTO.builder()
                .image(IMAGE)
                .tier("bronze")
                .email("moongchi@ssafy.com")
                .nickname("뭉치뭉치똥뭉치")
                .currentExp(1500)
                .nextTierExp(3000)
                .point(32000)
                .build();
        resultMap.put("member-info", member);

        BadgeMyPageResponseDTO badge = BadgeMyPageResponseDTO.builder()
                .image(IMAGE)
                .build();
        List<BadgeMyPageResponseDTO> bList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            bList.add(badge);
        }
        resultMap.put("badges", bList);

        StreakMyPageResponseDTO streak = StreakMyPageResponseDTO.builder()
                .date(LocalDate.now())
                .count(3)
                .build();

        List<StreakMyPageResponseDTO> sList = new ArrayList<>();
        for (int i = 0; i < 66; i++) {
            sList.add(streak);
        }
        resultMap.put("streak", sList);

        ChallengeMyPageResponseDTO challenge1 = ChallengeMyPageResponseDTO.builder()
                .name("김영한의 스프링 강의 정복")
                .startDate(LocalDateTime.now().minusDays(66))
                .category("강의")
                .build();

        ChallengeMyPageResponseDTO challenge2 = ChallengeMyPageResponseDTO.builder()
                .name("김태원의 5조")
                .startDate(LocalDateTime.now().minusDays(70))
                .category("알고리즘")
                .build();

        List<ChallengeMyPageResponseDTO> cList = new ArrayList<>();
        cList.add(challenge1);
        cList.add(challenge2);
        resultMap.put("challenge", cList);

        GroupMyPageResponseDTO group1 = GroupMyPageResponseDTO.builder()
                .name("뭉치뭉치똥뭉치")
                .badges(new ArrayList<String>(Arrays.asList("알고리즘", "CS")))
                .type("personal")
                .image(IMAGE)
                .build();

        GroupMyPageResponseDTO group2 = GroupMyPageResponseDTO.builder()
                .name("범블비식구들")
                .badges(new ArrayList<String>(Arrays.asList("알고리즘", "CS", "개발서적","블로깅","강의")))
                .type("group")
                .image(IMAGE)
                .build();

        List<GroupMyPageResponseDTO> gList = new ArrayList<>();
        gList.add(group1);
        gList.add(group2);
        resultMap.put("group", gList);


        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}