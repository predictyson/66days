package com.ssafy._66days.challenge.model.dto.requestDTO;

import lombok.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyChallengeRequestDTO {
    private Long challengeId;
    private String challengeName;
    private String content;

}
