package com.ssafy._66days.mono.group.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@IdClass(GroupAchievementId.class)
@Table(name = "group_achievement")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupAchievement {
	@Id
	@NotNull
	@OneToOne
	@JoinColumn(name = "challenge_id", nullable = false)
	private Challenge challenge;

	@Id
	@NotNull
	@OneToOne
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@NotNull
	@Column(name = "achievement_count")
	private int achievementCount;
}
