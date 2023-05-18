package com.ssafy._66days.mainservice.rank.model.repository;

import com.ssafy._66days.mainservice.rank.model.entity.ExpRank;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpRankRepository extends MongoRepository<ExpRank, String> {

	@Aggregation(pipeline = {"{$sort: {date: -1, exp: 1}}", "{$limit: 3}"})
	List<ExpRank> findTop3Rank();

	Optional<ExpRank> findByUserId(UUID id);
}
