package com.ssafy._66days.mainservice.user.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

	@Id
	@Column(columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@NotNull
	private Long animalId;

	@NotNull
	private Long tierId;

	@Email
	@NotBlank
	private String email;

	private String profileImagePath;

	@Size(max = 12)
	@NotBlank
	private String nickname;

	@NotNull
	@Min(0)
	private Long exp;

	@NotNull
	@Min(0)
	private Long point;

	@Column(length = 260)
	private String githubUrl;

	@Column(length = 260)
	private String blogUrl;

	@Builder
	public User(UUID userId, Long animalId, Long tierId, String email, String profileImagePath,
			    String nickname, Long exp, Long point, String githubUrl, String blogUrl) {
		this.userId = userId;
		this.animalId = animalId;
		this.tierId = tierId;
		this.email = email;
		this.profileImagePath = profileImagePath;
		this.nickname = nickname;
		this.exp = exp;
		this.point = point;
		this.githubUrl = githubUrl;
		this.blogUrl = blogUrl;
	}

	public void updatePoint(Long point) {
		this.point = point;
	}
}
