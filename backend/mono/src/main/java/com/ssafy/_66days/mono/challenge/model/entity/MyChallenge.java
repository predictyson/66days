package com.ssafy._66days.mono.challenge.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ssafy._66days.mono.user.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "my_challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyChallenge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "my_challenge_id")
	private Long myChallengeId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenge_id")
	private Challenge challenge;

	@NotBlank
	@Column(name = "challenge_name")
	private String challengeName;

	@NotBlank
	@Column(name = "content")
	private String content;

	@NotNull
	@Column(name = "start_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime startAt;

	@Column(name = "end_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime endAt;

	@NotBlank
	@Column(name = "state")
	private String state;
}
