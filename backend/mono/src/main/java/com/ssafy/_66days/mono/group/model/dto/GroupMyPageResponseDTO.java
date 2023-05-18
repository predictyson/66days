package com.ssafy._66days.mono.group.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import com.ssafy._66days.mono.group.model.entity.Group;

@Data
@Builder
public class GroupMyPageResponseDTO {
	private String image;
	private String name;
	private List<Challenge> challenges;

	public static GroupMyPageResponseDTO of(Group group, List<Challenge> challenges) {
		return GroupMyPageResponseDTO
				.builder()
				.image(group.getImagePath())
				.name(group.getGroupName())
				.challenges(challenges)
				.build();
	}
}
