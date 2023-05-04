package com.ssafy.mock66days.page.controller;

import com.ssafy.mock66days.article.model.dto.ArticleGroupPageResponseDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeGroupPageResponseDTO;
import com.ssafy.mock66days.badge.model.dto.BadgeMyPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeGroupPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeMainPageResponseDTO;
import com.ssafy.mock66days.challenge.model.dto.ChallengeMyPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupMainPageResponseDTO;
import com.ssafy.mock66days.group.model.dto.GroupMyPageResponseDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/")
@Api("PAGE API")
public class PageController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";
    @ApiOperation(value = "홈 화면", notes = "로그인 후 연결 되는 첫 페이지")
    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> getMainPage(
//            @RequestHeader(value = "Authorization") String token
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        MemberMainPageResponseDTO member = MemberMainPageResponseDTO.builder()
                .tier("bronze")
                .email("moongchi@ssafy.com")
                .exp(1500)
                .point(32000)
                .image("/image/카피바라.jpg")
                .animal("카피바라")
                .build();
        resultMap.put("member-info", member);

        ChallengeMainPageResponseDTO challenge1 = ChallengeMainPageResponseDTO.builder()
                .name("김영한의 스프링 강의 정복")
                .participants(new ArrayList<String>(Arrays.asList("뽀삐","뭉치","해피")))
                .startDate(LocalDate.now().minusDays(66))
                .status(false)
                .image(IMAGE)
                .build();

        ChallengeMainPageResponseDTO challenge2 = ChallengeMainPageResponseDTO.builder()
                .name("김태원의 5조")
                .participants(new ArrayList<String>(Arrays.asList("태원","귀렬","해피")))
                .startDate(LocalDate.now().minusDays(70))
                .status(true)
                .image(IMAGE)
                .build();

        List<ChallengeMainPageResponseDTO> cList = new ArrayList<>();
        cList.add(challenge1);
        cList.add(challenge2);
        resultMap.put("challenge", cList);

        GroupMainPageResponseDTO group1 = GroupMainPageResponseDTO.builder()
                .name("뭉치뭉치똥뭉치")
                .badges(new ArrayList<String>(Arrays.asList("알고리즘", "CS")))
                .type("personal")
                .image(IMAGE)
                .build();

        GroupMainPageResponseDTO group2 = GroupMainPageResponseDTO.builder()
                .name("범블비식구들")
                .badges(new ArrayList<String>(Arrays.asList("알고리즘", "CS", "개발서적","블로깅","강의")))
                .type("group")
                .image(IMAGE)
                .build();

        List<GroupMainPageResponseDTO> gList = new ArrayList<>();
        gList.add(group1);
        gList.add(group2);
        resultMap.put("group", gList);

        Member member1 = Member.builder()
                .image(IMAGE)
                .name("뭉치")
                .animal("카피바라")
                .tier("브론즈")
                .exp(1400)
                .badge(10)
                .build();

        Member member2 = Member.builder()
                .image(IMAGE)
                .name("해피")
                .animal("무너")
                .tier("브론즈")
                .exp(1000)
                .badge(13)
                .build();

        Member member3 = Member.builder()
                .image(IMAGE)
                .name("뽀삐")
                .animal("미니언즈")
                .tier("브론즈")
                .exp(800)
                .badge(12)
                .build();

        List<Member> expList = new ArrayList<>();
        expList.add(member1);
        expList.add(member2);
        expList.add(member3);
        List<Member> badgeList = new ArrayList<>();
        badgeList.add(member2);
        badgeList.add(member3);
        badgeList.add(member1);

        RankMainPageResponseDTO rank = RankMainPageResponseDTO.builder()
                .expRank(expList)
                .badgeRank(badgeList)
                .myExpRank(4)
                .myExp(600)
                .myBadgeRank(5)
                .myBadge(3)
                .build();

        resultMap.put("rank", rank);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "마이페이지", notes = "내 프로필 화면")
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMyPage(){
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

    @ApiOperation(value = "마이페이지", notes = "내 프로필 화면")
    @GetMapping("/group/{group_id}")
    public ResponseEntity<Map<String, Object>> getGroupPage(
            @PathVariable("group_id") @ApiParam(required = true) int groupId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("group-name","뭉치뭉치똥뭉치");

        BadgeGroupPageResponseDTO badge1 = BadgeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .category("알고리즘")
                .count(3)
                .build();
        BadgeGroupPageResponseDTO badge2 = BadgeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .category("CS")
                .count(3)
                .build();
        BadgeGroupPageResponseDTO badge3 = BadgeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .category("블로깅")
                .count(3)
                .build();
        BadgeGroupPageResponseDTO badge4 = BadgeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .category("강의")
                .count(3)
                .build();
        BadgeGroupPageResponseDTO badge5 = BadgeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .category("개발서적")
                .count(3)
                .build();
        List<BadgeGroupPageResponseDTO> bList = new ArrayList<>();
        bList.add(badge1);
        bList.add(badge2);
        bList.add(badge3);
        bList.add(badge4);
        bList.add(badge5);
        resultMap.put("badges", bList);

        Member member1 = Member.builder()
                .image(IMAGE)
                .name("뭉치")
                .animal("카피바라")
                .tier("브론즈")
                .exp(1400)
                .badge(10)
                .build();

        Member member2 = Member.builder()
                .image(IMAGE)
                .name("해피")
                .animal("무너")
                .tier("브론즈")
                .exp(1000)
                .badge(13)
                .build();

        Member member3 = Member.builder()
                .image(IMAGE)
                .name("뽀삐")
                .animal("미니언즈")
                .tier("브론즈")
                .exp(800)
                .badge(12)
                .build();

        List<Member> participants = new ArrayList<>();
        participants.add(member1);
        participants.add(member2);
        participants.add(member3);

        ChallengeGroupPageResponseDTO challenge1 = ChallengeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .name("김영한의 스프링 강의 정복")
                .maxParticipant(25)
                .participants(participants)
                .startDate(LocalDateTime.now().minusDays(66))
                .build();

        ChallengeGroupPageResponseDTO challenge2 = ChallengeGroupPageResponseDTO.builder()
                .image(IMAGE)
                .name("김태원의 5조")
                .maxParticipant(12)
                .participants(participants)
                .startDate(LocalDateTime.now().minusDays(70))
                .build();

        List<ChallengeGroupPageResponseDTO> cList = new ArrayList<>();
        cList.add(challenge1);
        cList.add(challenge2);
        resultMap.put("challenge", cList);

        ArticleGroupPageResponseDTO article1 = ArticleGroupPageResponseDTO.builder()
                .title("혹시 알고리즘 스터디 하실분?")
                .author("예바지보")
                .author_role("owner")
                .time(LocalDate.now())
                .build();

        ArticleGroupPageResponseDTO article2 = ArticleGroupPageResponseDTO.builder()
                .title("뽀삐는 천재에오")
                .author("천재견뽀삐")
                .author_role("manager")
                .time(LocalDate.now())
                .build();

        ArticleGroupPageResponseDTO article3 = ArticleGroupPageResponseDTO.builder()
                .title("CS 공부 할 사람~")
                .author("뭉치뭉치")
                .author_role("member")
                .time(LocalDate.now())
                .build();

        List<ArticleGroupPageResponseDTO> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        Map<String, Object> board = new HashMap<String, Object>();
        board.put("articles", articles);
        board.put("pageNo", 2);
        resultMap.put("board",board);

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}