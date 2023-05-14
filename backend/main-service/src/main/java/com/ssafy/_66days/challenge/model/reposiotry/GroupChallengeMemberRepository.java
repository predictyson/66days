package com.ssafy._66days.challenge.model.reposiotry;

import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.challenge.model.entity.GroupChallengeMemberId;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GroupChallengeMemberRepository extends JpaRepository<GroupChallengeMember, GroupChallengeMemberId> {
    List<GroupChallengeMember> findByUser(User user);

    List<GroupChallengeMember> findByGroupChallenge(GroupChallenge groupChallenge);

}
