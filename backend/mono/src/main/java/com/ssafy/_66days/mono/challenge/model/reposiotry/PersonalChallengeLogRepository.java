package com.ssafy._66days.mono.challenge.model.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

import com.ssafy._66days.mono.challenge.model.entity.mongodb.PersonalChallengeLog;

public interface PersonalChallengeLogRepository extends MongoRepository<PersonalChallengeLog, Long> {
	PersonalChallengeLog findByMyChallengeIdAndTime(Long myChallengeId, LocalDate tiem);
}
