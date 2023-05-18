package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GroupChallengeForGroupIntroPageResDTO {
	private final Long groupChallengeId;
	private final Long challengeId;
	private final String challengeContent;
	private final String challengeName;
	private final String challengeTopic;
	private final List<String> profileImagePathList;
	private final int maxMemberCount;
	private final int memberCount;
	private final LocalDate startAt;
	private final LocalDate endAt;
	private final String dDay;

	@Builder
	public GroupChallengeForGroupIntroPageResDTO(
			Long groupChallengeId, Long challengeId, String challengeContent,
			String challengeName, String challengeTopic, List<String> profileImagePathList, int maxMemberCount,
			int memberCount, LocalDate startAt, LocalDate endAt, String dDay) {
		this.groupChallengeId = groupChallengeId;
		this.challengeId = challengeId;
		this.challengeContent = challengeContent;
		this.challengeName = challengeName;
		this.challengeTopic = challengeTopic;
		this.profileImagePathList = profileImagePathList;
		this.maxMemberCount = maxMemberCount;
		this.memberCount = memberCount;
		this.startAt = startAt;
		this.endAt = endAt;
		this.dDay = dDay;
	}
}
