package com.ssafy._66days.article.controller;

import com.ssafy._66days.article.model.dto.ArticleDTO;
import com.ssafy._66days.article.model.dto.GroupCommentDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.ArticleRequestDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.ArticleUpdateDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.GroupCommentRequestDTO;
import com.ssafy._66days.article.model.service.ArticleService;
import com.ssafy._66days.article.model.service.GroupCommentService;
import com.ssafy._66days.article.model.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final GroupCommentService groupCommentService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService, GroupCommentService groupCommentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.groupCommentService = groupCommentService;
    }

    //게시글 작성 함수
    @PostMapping("/{group_id}")
    @ApiOperation(value = "게시글 등록, 로그인 O", notes = "그룹에 속한 사람만 게시글을 작성할 수 있다")
    public ResponseEntity<ArticleDTO> createArticle(
            @PathVariable("group_id") Long groupId,
            @RequestBody ArticleRequestDTO articleRequestDTO,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        // 존재하는 유저인지 확인
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 유저 닉네임을 알아낸 후 그 닉네임으로 유저 id를 알아낸다
        String userName = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(userName);
        try {
            // 유저가 작성한 게시글(제목, 내용)을 articleDTO에 담고 유저id롸 그룹id를 함께 넘겨 작성한다
            ArticleDTO createdArticle = articleService.createArticle(articleRequestDTO, userUuid, groupId);
            return ResponseEntity.status(HttpStatus.OK).body(createdArticle);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //게시글 조회 함수
    @GetMapping("/api/v1/groups/{group_id}/articles")
    @ApiOperation(value = "게시글 불러오기, 그룹 내 유저 O", notes = "그룹에 속한 유저라면 게시글을 보여준다(3개씩)")
    public ResponseEntity<List<ArticleDTO>> getArticles(
            @PathVariable("group_id") Long groupId,
            @RequestParam("offset") Long offset,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        // 존재하는 유저인지 확인
        // 없는 유저라면
        if (currentUser == null) {
            // not_found 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 있는 유저라면
        // 유저 닉네임을 알아낸 후 그 닉네임으로 id를 알아낸다
        String userName = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(userName);
        try {
            // groupId, userId, offset 값으로 게시글을 받아온다
            List<ArticleDTO> articles = articleService.findArticles(groupId, userUuid, offset);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/modify/{article_id}")
    @ApiOperation(value = "게시글 수정하기, 그룹 내 유저 O, 해당 글 작성한 유저 O", notes = "그룹에 속하면서 해당 글을 작성한 본인이면 수정할 수 있다")
    public ResponseEntity<ArticleDTO> updateArticle(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId,
            @RequestBody ArticleUpdateDTO articleUpdateDTO,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String userName = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(userName);
        try {
            ArticleDTO updatedArticle = articleService.updateArticle(groupId, articleId, userUuid, articleUpdateDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedArticle);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/{article_id}")
    @ApiOperation(value = "게시글 삭제 API", notes = "게시글 삭제")
    public ResponseEntity<Integer> deleteArticle(
            @PathVariable("group_id") Long groupId,
            @PathVariable("article_id") Long articleId,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String userName = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(userName);
        try {
            int isDeleted = articleService.deleteArticle(groupId, articleId, userUuid);
            return ResponseEntity.status(HttpStatus.OK).body(1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{article_id}/{group_id}")
    @ApiOperation(value = "댓글 작성 API", notes = "댓글 작성")
    public ResponseEntity<GroupCommentDTO> writeComment(
            @PathVariable("article_id") Long articleId,
            @PathVariable("group_id") Long groupId,
            @RequestBody GroupCommentRequestDTO groupCommentRequestDTO,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String userName = currentUser.getUsername();
        UUID userUuid = userService.getUserUuidByNickname(userName);
        try {
            GroupCommentDTO wroteComment = groupCommentService.writeComment(articleId, groupCommentRequestDTO, userUuid, groupId);
            return ResponseEntity.status(HttpStatus.OK).body(wroteComment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{article_id}")
    @ApiOperation(value = "댓글 조회 API", notes = "댓글 조회")
    public ResponseEntity<List<GroupCommentDTO>> getComments(
            @PathVariable("article_id") Long articleId,
            @RequestParam("offset") Long offset
    ) {

        try {
            List<GroupCommentDTO> comments = groupCommentService.findComments(articleId, offset);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/{article_id}/{comment_id}")
    @ApiOperation(value = "댓글 삭제 API", notes = "댓글 삭제")
    public ResponseEntity<Integer> deleteComment(
            @PathVariable("article_id") Long articleId,
            @PathVariable("comment_id") Long commentId,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try {
            int isDeleted = groupCommentService.deleteComment(articleId, commentId);
            return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}