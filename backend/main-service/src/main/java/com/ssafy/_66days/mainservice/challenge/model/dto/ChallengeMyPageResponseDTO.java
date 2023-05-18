package com.ssafy._66days.mainservice.challenge.model.dto;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeMyPageResponseDTO {
    private Long challengeId;
    private String topic;
    private String badgeImage;

    public static ChallengeMyPageResponseDTO of(Challenge challenge){
        return ChallengeMyPageResponseDTO
                .builder()
                .challengeId(challenge.getChallengeId())
                .topic(challenge.getTopic())
                .badgeImage(challenge.getBadgeImage())
                .build();
    }
}
