package com.ssafy._66days.mono.page.model.dto;

import com.ssafy._66days.mono.group.model.entity.Group;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MainPageGroupResponseDTO {

	private Long groupId;

	private String imagePath;

	private String name;

	private List<String> badges;

	public static MainPageGroupResponseDTO of(Group group, List<String> imagePath) {
		return MainPageGroupResponseDTO.builder()
				.groupId(group.getGroupId())
				.imagePath(group.getImagePath())
				.name(group.getGroupName())
				.badges(imagePath)
				.build();
	}
}
