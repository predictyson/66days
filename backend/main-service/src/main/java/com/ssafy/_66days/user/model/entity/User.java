package com.ssafy._66days.user.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

	@Id
	@Column(columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@NotNull
	@Column(name = "animal_id")
	private Long animalId;

	@NotNull
	@Column(name = "tier_id")
	private Long tierId;

	@NotBlank
	@Column(name = "email")
	private String email;

	@Column(name = "profile_image_path")
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
}
