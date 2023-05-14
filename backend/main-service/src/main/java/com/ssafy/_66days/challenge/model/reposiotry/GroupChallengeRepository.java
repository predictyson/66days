package com.ssafy._66days.challenge.model.reposiotry;

import com.ssafy._66days.challenge.model.entity.Challenge;
import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupChallengeRepository extends JpaRepository<GroupChallenge, Long> {
    GroupChallenge findByGroupAndChallengeAndState(Group group, Challenge challenge, String state);

    List<GroupChallenge> findByGroupAndStateIn(Group group, List<String> states);
}
