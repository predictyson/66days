package com.ssafy._66days.mono.challenge.model.reposiotry;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssafy._66days.mono.challenge.model.entity.mongodb.GroupChallengeLog;

public interface GroupChallengeLogRepository extends MongoRepository<GroupChallengeLog, Long> {
	GroupChallengeLog findByUserIdAndGroupChallengeIdAndTime(UUID userId, Long groupChallengeId, LocalDate time);
}
