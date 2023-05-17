package com.ssafy._66days.mainservice.badge.model.dto.ResponseDTO;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeMyPageDTO {
    private Long badgeId;
    private String imagePath;

    public static BadgeMyPageDTO of(Challenge challenge) {
        return BadgeMyPageDTO.builder()
                .badgeId(challenge.getChallengeId())
                .imagePath(challenge.getBadgeImage())
                .build();
    }
}
