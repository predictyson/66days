package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeApplication;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApplicationListResponseDTO {
	private String profileImagePath;
	private String nickname;

	public static ApplicationListResponseDTO of(GroupChallengeApplication applicant) {
		return ApplicationListResponseDTO.builder()
				.profileImagePath(applicant.getUser().getProfileImagePath())
				.nickname(applicant.getUser().getNickname())
				.build();
	}
}
