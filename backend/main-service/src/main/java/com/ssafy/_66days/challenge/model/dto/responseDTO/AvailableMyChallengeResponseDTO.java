package com.ssafy._66days.challenge.model.dto.responseDTO;

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

}
