package com.ssafy._66days.mono.article.model.dto.requestDto;

import com.ssafy._66days.mono.article.model.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleRequestDTO {
	private String title;
	private String content;

	public static ArticleRequestDTO of(Article article) {
		return ArticleRequestDTO.builder()
				.title(article.getTitle())
				.content(article.getContent())
				.build();
	}

}
