package com.ssafy._66days.mono.challenge.model.dto;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupChallengeMemberDTO {
	private String imagePath;
	private String nickName;
	private boolean isChecked;

	public static GroupChallengeMemberDTO of(GroupChallengeMember groupChallengeMember, boolean isChecked) {
		return GroupChallengeMemberDTO.builder()
				.imagePath(groupChallengeMember.getUser().getProfileImagePath())
				.nickName(groupChallengeMember.getUser().getNickname())
				.isChecked(isChecked)
				.build();
	}
}
