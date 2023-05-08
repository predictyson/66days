package com.ssafy._66days.article.model.dto.RequestDTO;

import com.ssafy._66days.article.model.entity.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleUpdateDTO {
    private String content;

    public static ArticleUpdateDTO of(Article article) {
        return ArticleUpdateDTO.builder()
                .content(article.getContent())
                .build();
    }
}
