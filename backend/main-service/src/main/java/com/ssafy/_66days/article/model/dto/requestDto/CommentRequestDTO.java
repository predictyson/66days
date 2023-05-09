package com.ssafy._66days.article.model.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentRequestDTO {
    private String content;
}
