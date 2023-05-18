package com.ssafy._66days.mono.badge.model.dto.ResponseDTO;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;

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
