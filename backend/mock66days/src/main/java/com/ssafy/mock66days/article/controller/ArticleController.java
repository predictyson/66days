package com.ssafy.mock66days.article.controller;

import com.ssafy.mock66days.article.model.dto.ArticleRequestDTO;
import com.ssafy.mock66days.article.model.dto.ArticleResponseDTO;
import com.ssafy.mock66days.article.model.dto.CommentDTO;
import com.ssafy.mock66days.group.model.dto.GroupSearchPageResponseDTO;
import com.ssafy.mock66days.member.model.dto.MemberManageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/article")
@Api("Article API")
public class ArticleController {

    private static final String SUCCESS = "success";
    private static final String RESULT = "result";
    private static final String IMAGE = "/image/image.jpg";

    @ApiOperation(value = "게시글 작성 API", notes = "게시글 작성")
    @PostMapping("/{group_id}")
    public ResponseEntity<Map<String, Object>> writeArticle(
            @PathVariable("group_id") @ApiParam(required = true) int groupId,
            @RequestParam("title") @ApiParam(required = true) String title,
            @RequestParam("content") @ApiParam(required = true) String content

    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        ArticleRequestDTO article = ArticleRequestDTO.builder()
                .title("알고리즘 챌린지원을 모집합니다.")
                .Content("안녕하세요! 그룹장 뭉치입니다. :D \n" +
                        "현재 알고리즘을 함꼐 풀 그룹원들을 모집하고 있습니다. \n" +
                        "주변에 관심있는 분들이 있으시다면 알려주시기 바랍니다! \n" +
                        "뭉치 올림")
                .build();
        resultMap.put("article-id",1);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 수정 API", notes = "게시글 수정")
    @PatchMapping("/modify/{article_id}")
    public ResponseEntity<Map<String, Object>> modifyArticle(
            @PathVariable("article_id") @ApiParam(required = true) int articleId,
            @RequestParam("content") @ApiParam(required = true) String content

    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        ArticleRequestDTO article = ArticleRequestDTO.builder()
                .title("알고리즘 챌린지원을 모집합니다.")
                .Content("안녕하세요! 그룹장 뭉치입니다. :D \n" +
                        "현재 알고리즘을 함꼐 풀 그룹원들을 모집하고 있습니다. \n" +
                        "주변에 관심있는 분들이 있으시다면 알려주시기 바랍니다! \n" +
                        "뭉치 올림")
                .build();
        resultMap.put("article-id",1);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제 API", notes = "게시글 삭제")
    @PatchMapping("/{article_id}")
    public ResponseEntity<Map<String, Object>> deleteArticle(
            @PathVariable("article_id") @ApiParam(required = true) int articleId,
            @RequestParam("content") @ApiParam(required = true) String content

    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 열람 API", notes = "게시글 열람")
    @GetMapping("/{article_id}")
    public ResponseEntity<Map<String, Object>> getArticle(
            @PathVariable("article_id") @ApiParam(required = true) int articleId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        CommentDTO comment1 = CommentDTO.builder()
                .commentId(1L)
                .image(IMAGE)
                .nickname("해피")
                .nickname("저두 신청합니다!")
                .date(LocalDate.now())
                .build();

        CommentDTO comment2 = CommentDTO.builder()
                .commentId(2L)
                .image(IMAGE)
                .nickname("뭉치")
                .nickname("주변에 하고 싶은 사람 있는지 찾아볼게요 :)")
                .date(LocalDate.now())
                .build();

        List<CommentDTO> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        ArticleResponseDTO article = ArticleResponseDTO.builder()
                .title("알고리즘 챌린지원을 모집합니다.")
                .content("안녕하세요! 그룹장 뭉치입니다. :D \n" +
                        "현재 알고리즘을 함꼐 풀 그룹원들을 모집하고 있습니다. \n" +
                        "주변에 관심있는 분들이 있으시다면 알려주시기 바랍니다! \n" +
                        "뭉치 올림")
                .nickname("뭉치")
                .date(LocalDate.now())
                .commentList(comments)
                .build();

        resultMap.put("article",article);
        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 작성 API", notes = "댓글 작성")
    @PostMapping("/{article_id}")
    public ResponseEntity<Map<String, Object>> writeComment(
            @PathVariable("article_id") @ApiParam(required = true) int articleId,
            @RequestParam("content") @ApiParam(required = true) String content

    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
    @ApiOperation(value = "댓글 삭제 API", notes = "댓글 삭제")
    @PatchMapping("/{comment_id}")
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable("comment_id") @ApiParam(required = true) int commentId
    ){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(RESULT, SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

}