package com.ssafy._66days.mono.challenge.model.reposiotry;

import com.ssafy._66days.mono.challenge.model.entity.mongodb.PersonalChallengeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface PersonalChallengeLogRepository extends MongoRepository<PersonalChallengeLog, Long> {
    PersonalChallengeLog findByMyChallengeIdAndTime(Long myChallengeId, LocalDate time);
}
