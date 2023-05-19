package com.ssafy._66days.mono.user.model.entity;

import com.ssafy._66days.mono.user.vo.ProviderType;
import com.ssafy._66days.mono.user.vo.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Builder
public class User {

	@Id
	@Column(name = "user_id", columnDefinition = "BINARY(16)", updatable = false)
	private UUID userId;

	@NotNull
	@Column(name = "animal_id")
	private Long animalId;

	@NotNull
	@Column(name = "tier_id")
	@ColumnDefault("1")
	private Long tierId;

	@Email
	@NotBlank
	@Column(name = "email", length = 320)
	private String email;

	@NotBlank
	@Column(name = "profile_image_path", length = 320)
	@ColumnDefault("/users/default.png")
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

	@Column(name = "social")
	@Enumerated(EnumType.STRING)
	private ProviderType social;

	@Column(name = "ROLE_TYPE", length = 20)
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

//	public User(UUID userId, Long animalId, Long tierId, String email, String profileImagePath,
//				String nickname, Long exp, Long point, String githubUrl, String blogUrl) {
//		this.userId = userId;
//		this.animalId = animalId;
//		this.tierId = tierId;
//		this.email = email;
//		this.profileImagePath = profileImagePath;
//		this.nickname = nickname;
//		this.exp = exp;
//		this.point = point;
//		this.githubUrl = githubUrl;
//		this.blogUrl = blogUrl;
//	}

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
