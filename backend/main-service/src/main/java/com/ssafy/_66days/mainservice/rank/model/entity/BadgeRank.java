package com.ssafy._66days.mainservice.rank.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "badge_rank")
@CompoundIndex(name = "date_rank_index", def = "{ 'date': -1, 'rank': 1 }")
public class BadgeRank {

	private LocalDateTime date;
	private Integer rank;

	@Indexed

	private String email;

	private String nickname;

	private String tierName;

	private String animalName;

	private Long exp;

	@Builder
	public BadgeRank(
			LocalDateTime date, Integer rank,
			String email, String nickname, String tierName,
			String animalName, Long exp) {
		this.date = date;
		this.rank = rank;
		this.email = email;
		this.nickname = nickname;
		this.tierName = tierName;
		this.animalName = animalName;
		this.exp = exp;
	}
}
