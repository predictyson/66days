package com.ssafy._66days.mainservice.group.model.repository;

import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupAchievement;
import com.ssafy._66days.mainservice.group.model.entity.GroupAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupAchievementRepository extends JpaRepository<GroupAchievement, GroupAchievementId> {
    List<GroupAchievement> findByGroup(Group group);
}
