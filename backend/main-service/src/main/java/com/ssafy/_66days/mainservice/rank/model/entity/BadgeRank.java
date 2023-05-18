package com.ssafy._66days.mainservice.rank.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

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
	@Id
	private UUID userId;

	private String nickname;

	private String tierName;

	private String animalName;

	private Integer badgeCount;

	@Builder
	public BadgeRank(
			LocalDateTime date, Integer rank,
			UUID userId, String nickname, String tierName,
			String animalName, Integer badgeCount) {
		this.date = date;
		this.rank = rank;
		this.userId = userId;
		this.nickname = nickname;
		this.tierName = tierName;
		this.animalName = animalName;
		this.badgeCount = badgeCount;
	}
}
