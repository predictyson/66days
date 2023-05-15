package com.ssafy._66days.mainservice.challenge.model.dto.responseDTO;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AvailableGroupChallengeResponseDTO {
    private Long challengeId;
    private String imagePath;
    private String topic;

    public static AvailableGroupChallengeResponseDTO of(Challenge challenge) {
        return AvailableGroupChallengeResponseDTO.builder()
                .challengeId(challenge.getChallengeId())
                .imagePath(challenge.getBadge().getImagePath())
                .topic(challenge.getTopic())
                .build();
    }

}
