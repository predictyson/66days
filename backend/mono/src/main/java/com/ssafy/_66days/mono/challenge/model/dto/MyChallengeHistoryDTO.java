package com.ssafy._66days.mono.challenge.model.dto;

import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MyChallengeHistoryDTO {
	private String challengeName;
	private boolean isSuccess;

	public static MyChallengeHistoryDTO of(MyChallenge myChallenge, boolean isSuccess) {
		return MyChallengeHistoryDTO.builder()
				.challengeName(myChallenge.getChallengeName())
				.isSuccess(isSuccess)
				.build();
	}
}
