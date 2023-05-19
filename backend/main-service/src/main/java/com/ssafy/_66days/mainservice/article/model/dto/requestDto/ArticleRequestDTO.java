package com.ssafy._66days.mainservice.article.model.dto.requestDto;

import com.ssafy._66days.mainservice.article.model.entity.Article;
import lombok.*;

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
