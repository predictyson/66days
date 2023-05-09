package com.ssafy._66days.group.model.repository;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupApplyRepository extends JpaRepository <GroupApply, Long> {
    List<GroupApply> findAllByStateAndGroup(String waiting, Group group);
}
