package com.ssafy.mock66days.article.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ArticleResponseDTO {
    private String title;
    private String content;
    private String nickname;
    private List<CommentDTO> commentList;
    private LocalDate date;
}
