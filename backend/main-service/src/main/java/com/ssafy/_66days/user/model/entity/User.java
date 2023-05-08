package com.ssafy._66days.user.model.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	@GenericGenerator(name="uuid", strategy="uuid4")
	@Column(name = "user_id")
	private UUID userId;

	@NotNull
	@Column(name = "animal_id")
	private Long animalId;

	@NotNull
	@Column(name = "tier_id")
	private Long tierId;

	@NotNull
	@Column(name = "email")
	private String email;

	@Column(name = "profile_image_path")
	private String profileImagePath;

	@NotNull
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

	@Column(name = "github_url")
	private String githubUrl;

	@Column(name = "blog_url")
	private String blogUrl;
}
