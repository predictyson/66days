package com.ssafy._66days.mainservice.tier.model.dto;

import lombok.Builder;

public class TierMainPageResponseDTO {

	private String imagePath;

	private String title;

	@Builder
	public TierMainPageResponseDTO(String imagePath, String title) {
		this.imagePath = imagePath;
		this.title = title;
	}
}
