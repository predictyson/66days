package com.ssafy._66days.mono.challenge.model.reposiotry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeMemberId;
import com.ssafy._66days.mono.user.model.entity.User;

public interface GroupChallengeMemberRepository extends JpaRepository<GroupChallengeMember, GroupChallengeMemberId> {
	List<GroupChallengeMember> findByUser(User user);

	List<GroupChallengeMember> findByGroupChallenge(GroupChallenge groupChallenge);

	Optional<GroupChallengeMember> findByUserAndGroupChallenge(User user, GroupChallenge groupChallenge);

}
