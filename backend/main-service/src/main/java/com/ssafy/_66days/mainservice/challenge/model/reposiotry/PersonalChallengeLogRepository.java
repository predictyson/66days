package com.ssafy._66days.mainservice.challenge.model.reposiotry;

import com.ssafy._66days.mainservice.challenge.model.entity.mongodb.PersonalChallengeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface PersonalChallengeLogRepository extends MongoRepository<PersonalChallengeLog, Long> {
    PersonalChallengeLog findByMyChallengeIdAndTime(Long myChallengeId, LocalDate tiem);
}
