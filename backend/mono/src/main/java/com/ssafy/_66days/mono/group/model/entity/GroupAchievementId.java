package com.ssafy._66days.mono.group.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAchievementId implements Serializable {
	private Long group;
	private Long challenge;
}
