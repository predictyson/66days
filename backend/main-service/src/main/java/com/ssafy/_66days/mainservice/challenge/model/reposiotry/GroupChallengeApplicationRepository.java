package com.ssafy._66days.mainservice.challenge.model.reposiotry;

import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallengeApplication;
import com.ssafy._66days.mainservice.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupChallengeApplicationRepository extends JpaRepository<GroupChallengeApplication, Long> {
    Optional<GroupChallengeApplication> findByUserAndGroupChallenge(User user, GroupChallenge groupChallenge);

    GroupChallengeApplication findByUserAndGroupChallengeAndState(User user, GroupChallenge groupChallenge, String state);
}
