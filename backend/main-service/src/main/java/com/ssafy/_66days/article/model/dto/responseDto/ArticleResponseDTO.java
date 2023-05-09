package com.ssafy._66days.article.model.dto.responseDto;

import com.ssafy._66days.article.model.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleResponseDTO {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UUID userId;
    private String nickname;
    private Long groupId;


    public static ArticleResponseDTO of(Article article) {
        return ArticleResponseDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .userId(article.getUser().getUserId())
                .nickname(article.getUser().getNickname())
                .groupId(article.getGroup().getGroupId())
                .build();
    }

    public static ArticleResponseDTO from(Article article) {
        return ArticleResponseDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .userId(article.getUser().getUserId())
                .nickname(article.getUser().getNickname())
                .groupId(article.getGroup().getGroupId())
                .build();
    }
}
