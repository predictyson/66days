package com.ssafy._66days.mainservice.challenge.model.dto.responseDTO;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableMyChallengeResponseDTO {
    private Long challengeId;
    private String challengeName;
    private String imagePath;
    private boolean available;

    public static AvailableMyChallengeResponseDTO of(Challenge challenge, boolean isAvailable) {
        return AvailableMyChallengeResponseDTO.builder()
                .challengeId(challenge.getChallengeId())
                .challengeName(challenge.getTopic())
                .imagePath(challenge.getBadge().getImagePath())
                .available(isAvailable)
                .build();
    }

}
