package com.ssafy._66days.mainservice.user.model.dto;

import java.util.List;
import java.util.UUID;

import com.ssafy._66days.mainservice.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mainservice.page.model.dto.MainPageGroupResponseDTO;
import com.ssafy._66days.mainservice.tier.model.dto.TierMainPageResponseDTO;

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

	private MainPageGroupResponseDTO myGroup;

	private List<MainPageGroupResponseDTO> groups;

	@Builder
	public UserDetailResponseDTO(UUID userId, String email, String nickname, Long exp,
								 Long point, String profileImagePath, TierMainPageResponseDTO tier,
								 AnimalMainPageResponseDTO animal, MainPageGroupResponseDTO myGroup,
								 List<MainPageGroupResponseDTO> groups) {
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
