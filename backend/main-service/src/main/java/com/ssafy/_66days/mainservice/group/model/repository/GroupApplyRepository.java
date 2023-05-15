package com.ssafy._66days.mainservice.group.model.repository;

import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupApply;
import com.ssafy._66days.mainservice.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupApplyRepository extends JpaRepository <GroupApply, Long> {
    List<GroupApply> findAllByStateAndGroup(String state, Group group);

    GroupApply findByUserAndGroup(User user, Group group);
}
