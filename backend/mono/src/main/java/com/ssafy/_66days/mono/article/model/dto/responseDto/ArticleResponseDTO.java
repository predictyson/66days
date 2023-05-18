package com.ssafy._66days.mono.article.model.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ssafy._66days.mono.article.model.entity.Article;

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
	private String role;

	public static ArticleResponseDTO of(Article article, String role) {
		return ArticleResponseDTO.builder()
				.articleId(article.getArticleId())
				.title(article.getTitle())
				.content(article.getContent())
				.createdAt(article.getCreatedAt())
				.userId(article.getUser().getUserId())
				.nickname(article.getUser().getNickname())
				.groupId(article.getGroup().getGroupId())
				.role(role)
				.build();
	}
}
