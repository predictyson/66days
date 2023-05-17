package com.ssafy._66days.mono.user.model.dto;

import java.util.List;
import java.util.UUID;

import com.ssafy._66days.mono.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mono.group.model.dto.GroupMainPageResponseDTO;
import com.ssafy._66days.mono.tier.model.dto.TierMainPageResponseDTO;

import lombok.Builder;

public class UserDetailResponseDTO {

	private UUID userId;

	private String email;

	private String nickname;

	private Long exp;

	private Long point;

	private String profileImagePath;

	private TierMainPageResponseDTO tier;

	private AnimalMainPageResponseDTO animal;

	private GroupMainPageResponseDTO myGroup;

	private List<GroupMainPageResponseDTO> groups;

	@Builder
	public UserDetailResponseDTO(UUID userId, String email, String nickname, Long exp,
								 Long point, String profileImagePath, TierMainPageResponseDTO tier,
								 AnimalMainPageResponseDTO animal, GroupMainPageResponseDTO myGroup,
								 List<GroupMainPageResponseDTO> groups) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.exp = exp;
		this.point = point;
		this.profileImagePath = profileImagePath;
		this.tier = tier;
		this.animal = animal;
		this.myGroup = myGroup;
		this.groups = groups;
	}
}
