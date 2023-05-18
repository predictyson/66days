package com.ssafy._66days.mono.group.model.repository;

import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupApply;
import com.ssafy._66days.mono.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupApplyRepository extends JpaRepository <GroupApply, Long> {
    List<GroupApply> findAllByStateAndGroup(String state, Group group);

    Optional<GroupApply> findByUserAndGroup(User user, Group group);

    Optional<GroupApply> findByUserAndGroupAndState(User user, Group group, String state);
}
