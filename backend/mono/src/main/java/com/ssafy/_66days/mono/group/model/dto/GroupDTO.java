package com.ssafy._66days.mono.group.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupDTO {
	private Long groupId;
	private String groupName;
	private int maxMemberCount;
	private String imagePath;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean isDeleted;

}
