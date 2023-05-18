package com.ssafy._66days.mono.tier.model.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "tier")
public class Tier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column
	private Long tierId;

	@NotNull
	@Column
	private String name;

	@NotNull
	@Column
	private String imagePath;

	@NotNull
	@Column
	private String title;

	@NotNull
	@Column
	private Long requiredExp;
}
