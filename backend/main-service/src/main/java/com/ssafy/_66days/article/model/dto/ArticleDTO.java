package com.ssafy._66days.article.model.dto;

import com.ssafy._66days.article.model.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ArticleDTO {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int isDeleted;
    private UUID userId;
    private Long groupId;


    public static ArticleDTO of(Article article) {
        return ArticleDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .isDeleted(article.getIsDeleted())
                .userId(article.getUser().getUserId())
                .groupId(article.getGroup().getGroupId())
                .build();
    }

    public static ArticleDTO from(Article article) {
        return ArticleDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .isDeleted(article.getIsDeleted())
                .userId(article.getUser().getUserId())
                .groupId(article.getGroup().getGroupId())
                .build();
    }
}
