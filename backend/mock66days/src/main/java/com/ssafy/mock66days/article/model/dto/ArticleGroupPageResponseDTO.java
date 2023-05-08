package com.ssafy.mock66days.article.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ArticleGroupPageResponseDTO {
    private String title;
    private String author;
    private String author_role;
    private LocalDate time;
}
