package com.ssafy._66days.mono.page.model.dto;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MyPageBadgeDTO {
    private Long challengeId;
    private String imagePath;
    private int number;

    public static MyPageBadgeDTO of(Challenge challenge, int number) {
        return MyPageBadgeDTO.builder()
                .challengeId(challenge.getChallengeId())
                .imagePath(challenge.getBadgeImage())
                .number(number)
                .build();
    }
}
