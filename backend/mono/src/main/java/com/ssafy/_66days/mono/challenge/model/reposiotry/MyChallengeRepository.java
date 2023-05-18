package com.ssafy._66days.mono.challenge.model.reposiotry;

import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mono.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MyChallengeRepository extends JpaRepository<MyChallenge, Long> {
    List<MyChallenge> findByUser(User user);

    List<MyChallenge> findByState(String state);

    List<MyChallenge> findByUserAndState(User user, String state);

    Optional<MyChallenge> findByMyChallengeIdAndUser(Long myChallengeId, UUID user);

    Optional<MyChallenge> findByMyChallengeIdAndState(Long myChallengeId, String state);
}
