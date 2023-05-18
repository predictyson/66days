package com.ssafy._66days.mono.group.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.ssafy._66days.mono.group.model.entity.Group;

@Data
@Builder
public class GroupCreateDTO {
	private Long groupId;
	private String name;
	private String description;
	private int maxMember;
	private String image;
	private LocalDateTime createTime;
	private boolean isDeleted;

	public static Group toEntity(GroupCreateDTO groupCreateDTO) {
		return Group.builder()
				.groupName(groupCreateDTO.getName())
				.description(groupCreateDTO.getDescription())
				.maxMemberCount(groupCreateDTO.getMaxMember())
				.imagePath(groupCreateDTO.getImage())
				.createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
				.build();
	}
}
