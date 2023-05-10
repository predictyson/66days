package com.ssafy._66days.article.controller;

import com.ssafy._66days.article.model.dto.responseDto.ArticleResponseDTO;
import com.ssafy._66days.article.model.dto.responseDto.CommentResponseDTO;
import com.ssafy._66days.article.model.dto.requestDto.ArticleRequestDTO;
import com.ssafy._66days.article.model.dto.requestDto.CommentRequestDTO;
import com.ssafy._66days.article.model.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;

    }

    //게시글 작성 함수
    @PostMapping("/{group_id}")
    @ApiOperation(value = "게시글 작성 API", notes = "그룹 게시판에 게시글 작성")
    public ResponseEntity<Map<String, Object>> createArticle (
            @PathVariable("group_id") Long groupId,
            @RequestBody ArticleRequestDTO articleRequestDTO
    ) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // 유저가 작성한 게시글(제목, 내용)을 articleDTO에 담고 유저id롸 그룹id를 함께 넘겨 작성한다
            ArticleResponseDTO createdArticle = articleService.createArticle(groupId, articleRequestDTO);
            resultMap.put("createdArticle", createdArticle);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }
    @GetMapping("/{group_id}/{article_id}")
    @ApiOperation(value = "게시글 상세 조회 API", notes = "게시글 상세 조회")
    public ResponseEntity<Map<String, Object>> getArticleDetail(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // groupId, articleId 값으로 게시글을 받아온다
            ArticleResponseDTO articleResponseDTO = articleService.getArticle(groupId, articleId);
            resultMap.put("articleDTO", articleResponseDTO);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    //게시글 조회 함수
    @GetMapping("/{group_id}/articles")
    @ApiOperation(value = "게시글 조회 API", notes = "속한 그룹의 게시글 3개씩 조회")
    public ResponseEntity<Map<String, Object>> getArticles(
            @PathVariable("group_id") Long groupId,
            @RequestParam("offset") int offset
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // groupId, userId, offset 값으로 게시글을 받아온다
            List<ArticleResponseDTO> articles = articleService.getArticleList(groupId, offset);
            resultMap.put("articles", articles);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    @PatchMapping("/{group_id}/modify/{article_id}")
    @ApiOperation(value = "게시글 수정 API", notes = "자신이 작성한 게시글 수정")
    public ResponseEntity<Map<String, Object>> updateArticle(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId,
            @RequestBody ArticleRequestDTO articleRequestDTO

    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            ArticleResponseDTO updatedArticle = articleService.updateArticle(groupId, articleId, articleRequestDTO);
            resultMap.put("updatedArticle", updatedArticle);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    @PatchMapping("/{group_id}/delete/{article_id}")
    @ApiOperation(value = "게시글 삭제 API", notes = "자신이 작성한 게시글 삭제")
    public ResponseEntity<Map<String, Object>> deleteArticle(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            boolean isDeleted = articleService.deleteArticle(groupId, articleId);
            resultMap.put("isDeleted", isDeleted);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isDeleted", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    @PostMapping("/{group_id}/{article_id}/comment")
    @ApiOperation(value = "댓글 작성 API", notes = "게시글에 댓글 작성")
    public ResponseEntity<Map<String, Object>> writeComment(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId,
            @RequestBody CommentRequestDTO commentRequestDTO
    ) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            CommentResponseDTO wroteComment = articleService.writeComment(groupId, articleId, commentRequestDTO);
            resultMap.put("wroteComment", wroteComment);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    @GetMapping("/{article_id}/comments")
    @ApiOperation(value = "댓글 조회 API", notes = "댓글 10개씩 조회")
    public ResponseEntity<Map<String, Object>> getComments(
            @PathVariable("article_id") Long articleId,
            @RequestParam("offset") int offset
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<CommentResponseDTO> commentsList = articleService.getCommentList(articleId, offset);
            resultMap.put("commentsList", commentsList); // 빈 리스트가 반환됟 수 있음
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    @PatchMapping("/{article_id}/{comment_id}")
    @ApiOperation(value = "댓글 삭제 API", notes = "자신이 쓴 댓글 삭제")
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable("article_id") Long articleId,
            @PathVariable("comment_id") Long commentId
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            boolean isDeleted = articleService.deleteComment(articleId, commentId);
            resultMap.put("isDeleted", isDeleted);
            return ResponseEntity.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }

    }
}