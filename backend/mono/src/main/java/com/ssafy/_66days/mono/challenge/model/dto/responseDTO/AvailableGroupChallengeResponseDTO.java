package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
				.imagePath(challenge.getBadgeImage())
				.topic(challenge.getTopic())
				.build();
	}

}
