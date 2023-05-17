package com.ssafy._66days.mainservice.user.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

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
	@Column(name = "user_id", columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@NotNull
	@Column(name = "animal_id")
	private Long animalId;

	@NotNull
	@Column(name = "tier_id")
	private Long tierId;

	@Email
	@NotBlank
	@Column(name = "email", length = 320)
	private String email;

	@NotBlank
	@Column(name = "profile_image_path", length = 320)
	private String profileImagePath;

	@Size(max = 12)
	@NotBlank
	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "exp")
	@ColumnDefault("0")
	private Long exp;

	@NotNull
	@Column(name = "point")
	@ColumnDefault("0")
	private Long point;

	@Column(name = "github_url", length = 260)
	private String githubUrl;

	@Column(name = "blog_url", length = 260)
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
	public void updateImage(String profileImagePath){
		this.profileImagePath = profileImagePath;
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}
}
