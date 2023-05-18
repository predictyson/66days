package com.ssafy._66days.mono.group.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupMainPageResponseDTO {

	private Long groupId;

	private String imagePath;

	private String name;

	private List<String> badges;

	@Builder
	public GroupMainPageResponseDTO(Long groupId, String imagePath, String name, List<String> badges) {
		this.groupId = groupId;
		this.imagePath = imagePath;
		this.name = name;
		this.badges = badges;
	}
}
