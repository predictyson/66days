package com.ssafy._66days.mono.challenge.model.reposiotry;

import com.ssafy._66days.mono.challenge.model.entity.mongodb.GroupChallengeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface GroupChallengeLogRepository extends MongoRepository<GroupChallengeLog, Long> {
    GroupChallengeLog findByUserIdAndGroupChallengeIdAndTime(UUID userId, Long groupChallengeId, LocalDate time);
}
