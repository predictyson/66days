package com.ssafy._66days.mono.badge.model.dto.ResponseDTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BadgeListResponseDTO {
	private Long badgeId;
	private String badgeName;
	private String description;
	private String imagePath;
}
