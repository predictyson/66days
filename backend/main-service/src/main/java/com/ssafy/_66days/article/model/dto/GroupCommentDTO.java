package com.ssafy._66days.article.model.dto;

import com.ssafy._66days.article.model.entity.GroupComment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupCommentDTO {
    private Long commentId;
    private Long articleId;
    private Long groupId;
    private UUID userId;
    private String content;
    private LocalDateTime createdAt;
    private int isDeleted;

    public static GroupCommentDTO of(GroupComment groupComment) {
        return GroupCommentDTO.builder()
                .commentId(groupComment.getCommentId())
                .articleId(groupComment.getArticle().getArticleId())
                .groupId(groupComment.getGroup().getGroupId())
                .userId(groupComment.getUser().getUserId())
                .content(groupComment.getContent())
                .createdAt(groupComment.getCreatedAt())
                .isDeleted(groupComment.getIsDeleted())
                .build();
    }
    public static GroupCommentDTO from(GroupComment groupComment) {
        return GroupCommentDTO.builder()
                .commentId(groupComment.getCommentId())
                .articleId(groupComment.getArticle().getArticleId())
                .groupId(groupComment.getGroup().getGroupId())
                .userId(groupComment.getUser().getUserId())
                .content(groupComment.getContent())
                .createdAt(groupComment.getCreatedAt())
                .isDeleted(groupComment.getIsDeleted())
                .build();
    }

}

