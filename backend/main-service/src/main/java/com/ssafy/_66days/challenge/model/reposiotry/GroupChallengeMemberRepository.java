package com.ssafy._66days.challenge.model.reposiotry;

import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GroupChallengeMemberRepository extends JpaRepository<GroupChallenge, Long> {
    List<GroupChallengeMember> findByUser(User user);
}
