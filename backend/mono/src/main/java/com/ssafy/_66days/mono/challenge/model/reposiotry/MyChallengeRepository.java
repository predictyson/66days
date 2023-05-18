package com.ssafy._66days.mainservice.challenge.model.reposiotry;

import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mainservice.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MyChallengeRepository extends JpaRepository<MyChallenge, Long> {
    List<MyChallenge> findByUser(User user);

    List<MyChallenge> findByState(String state);

    List<MyChallenge> findByUserAndState(User user, String state);

    Optional<MyChallenge> findByMyChallengeIdAndUser(Long myChallengeId, UUID user);

    Optional<MyChallenge> findByMyChallengeIdAndState(Long myChallengeId, String state);

    List<MyChallenge> findDistinctChallengeIdByUserAndState(User user, String success);

    List<MyChallenge> findByChallengeAndStateIn(Challenge challenge, List<String> state);
}
