package com.ssafy._66days.mainservice.animal.model.dto;

import lombok.Builder;

public class AnimalMainPageResponseDTO {

	private Long animalId;

	private String name;

	private String imagePath;

	@Builder
	public AnimalMainPageResponseDTO(Long animalId, String name, String imagePath) {
		this.animalId = animalId;
		this.name = name;
		this.imagePath = imagePath;
	}
}
