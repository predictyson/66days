package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;

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
				.imagePath(challenge.getBadgeImage())
				.available(isAvailable)
				.build();
	}

}
