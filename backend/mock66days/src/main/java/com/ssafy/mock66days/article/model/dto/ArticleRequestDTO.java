package com.ssafy.mock66days.article.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleRequestDTO {
    private String title;
    private String Content;
}
