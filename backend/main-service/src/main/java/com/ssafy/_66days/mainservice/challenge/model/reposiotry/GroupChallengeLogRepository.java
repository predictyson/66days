package com.ssafy._66days.mainservice.challenge.model.reposiotry;

import com.ssafy._66days.mainservice.challenge.model.entity.mongodb.GroupChallengeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.UUID;

public interface GroupChallengeLogRepository extends MongoRepository<GroupChallengeLog, Long> {

//    GroupChallengeLog findByGroupChallengeIdAndTimeAndUserId(Long groupChallengeId, Date today, UUID tempUserId);

    GroupChallengeLog findByGroupChallengeIdAndTimeAndUserIdAndTimeBetween(Long groupChallengeId, Date today, UUID tempUserId, Date startOfToday, Date endOfToday);

}
