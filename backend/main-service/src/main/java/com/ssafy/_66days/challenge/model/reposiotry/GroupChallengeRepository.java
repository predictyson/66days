package com.ssafy._66days.challenge.model.reposiotry;

import com.ssafy._66days.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface GroupChallengeRepository extends JpaRepository<GroupChallenge, Long> {

//    @Query("SELECT gcm.groupChallenge FROM GroupChallenge gc " +
//            "JOIN GroupChallengeMember gcm ON gc.groupChallengeId = gcm.groupChallenge " +
//            "WHERE g.groupId = :groupId AND gcm.user = :userId")
//    List<Long> findByGroupAndUser(@Param("userId") UUID userId, @Param("groupId") Long groupId);


    @Query("SELECT gcm.groupChallenge.groupChallengeId FROM GroupChallenge gc " +
//            "JOIN gc.group g " +
            "JOIN GroupChallengeMember gcm ON gc.groupChallengeId = gcm.groupChallenge.groupChallengeId " +
            "WHERE g.groupId = :groupId AND gcm.user.userId = :userId")
    List<Long> findByGroupAndUser(@Param("userId") UUID userId, @Param("groupId") Long groupId);

}
