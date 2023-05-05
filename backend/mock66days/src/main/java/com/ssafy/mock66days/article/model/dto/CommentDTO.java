package com.ssafy.mock66days.article.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentDTO {
    private Long commentId;
    private String image;
    private String nickname;
    private String content;
    private LocalDate date;
}
