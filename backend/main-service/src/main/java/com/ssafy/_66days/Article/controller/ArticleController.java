package com.ssafy._66days.Article.controller;

import com.ssafy._66days.Article.model.entity.Article;
import com.ssafy._66days.Article.model.service.ArticleService;
import com.ssafy._66days.Article.model.service.UserService;
import com.ssafy._66days.commom.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "게시글 등록, 로그인 O", notes = "그룹에 속한 사람만 게시글을 작서할 수 있다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "등록 실패[ 이미 등록된 상태 ]"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> createArticle(@RequestBody Article article, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(404, "USER NOT FOUND"));
        }
        article.setUser(userService.getUserByUserUuid(currentUser.getUuid()));
        Article createArticle = articleService.createArticle(article, currentUser);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));

    }
}
