package com.ssafy._66days.mono.challenge.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy._66days.mono.user.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@IdClass(GroupChallengeMemberId.class)
@Entity
@Table(name = "group_challenge_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChallengeMember {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_challenge_id")
	private GroupChallenge groupChallenge;
}
