package com.ssafy._66days.mono.challenge.model.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.group.model.entity.Group;

public interface GroupChallengeRepository extends JpaRepository<GroupChallenge, Long> {
	List<GroupChallenge> findByGroupAndChallengeAndState(Group group, Challenge challenge, String state);

	List<GroupChallenge> findByGroupAndStateIn(Group group, List<String> states);

	Optional<GroupChallenge> findByGroupChallengeIdAndState(Long groupChallengeId, String State);

	List<Challenge> findAllByGroup(Group group);

	long countByGroupAndStateIn(Group group, List<String> states);
}
