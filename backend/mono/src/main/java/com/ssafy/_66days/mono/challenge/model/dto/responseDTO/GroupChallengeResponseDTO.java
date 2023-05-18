package com.ssafy._66days.mono.challenge.model.dto.responseDTO;

import lombok.*;

import java.util.Date;
import java.util.List;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupChallengeResponseDTO {
	private Long groupChallengeId;
	private Long challengeId;
	private String imagePath;
	private String challengeName;
	private Date startAt;
	private int maxMemberCount;
	private int memberCount;
	private List<String> profileImagePathList;

	public static GroupChallengeResponseDTO of(
			GroupChallenge groupChallenge,
			Date startAt,
			int memberCount,
			List<String> profileImagePathList
	) {
		return GroupChallengeResponseDTO.builder()
				.groupChallengeId(groupChallenge.getGroupChallengeId())
				.challengeId(groupChallenge.getChallenge().getChallengeId())
				.imagePath(groupChallenge.getChallenge().getBadgeImage())
				.challengeName(groupChallenge.getChallengeName())
				.startAt(startAt)
				.maxMemberCount(groupChallenge.getMaxMemberCount())
				.memberCount(memberCount)
				.profileImagePathList(profileImagePathList)
				.build();
	}
}
