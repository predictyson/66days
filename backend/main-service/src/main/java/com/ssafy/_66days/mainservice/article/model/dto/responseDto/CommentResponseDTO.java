package com.ssafy._66days.mainservice.article.model.dto.responseDto;

import com.ssafy._66days.mainservice.article.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponseDTO {
    private Long commentId;
    private Long articleId;
    private Long groupId;
    private UUID userId;
    private String nickname;
    private String profileImagePath;
    private String content;
    private LocalDateTime createdAt;

    public static CommentResponseDTO of(Comment comment) {
        return CommentResponseDTO.builder()
                .commentId(comment.getCommentId())
                .articleId(comment.getArticle().getArticleId())
                .groupId(comment.getGroup().getGroupId())
                .userId(comment.getUser().getUserId())
                .nickname(comment.getUser().getNickname())
                .profileImagePath(comment.getUser().getProfileImagePath())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}

