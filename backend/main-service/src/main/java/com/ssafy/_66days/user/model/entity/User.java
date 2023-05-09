package com.ssafy._66days.user.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Builder
public class User {
	@Id
	@GeneratedValue
	@GenericGenerator(name="uuid", strategy="uuid2")
	@Column(name = "user_id", columnDefinition = "BINARY(16)")
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

	@Column(name = "github_url")
	private String githubUrl;

	@Column(name = "blog_url")
	private String blogUrl;
}
