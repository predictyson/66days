package com.ssafy._66days.mainservice.rank.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssafy._66days.mainservice.rank.model.entity.BadgeRank;

public interface BadgeRankRepository extends MongoRepository<BadgeRank, String> {

	@Aggregation(pipeline = {
			"{$sort: {date: -1, exp: 1}}",
			"{$limit: 3}"
	})
	List<BadgeRank> findTop3Rank();

	Optional<BadgeRank> findByEmail();
}
