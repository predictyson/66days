package com.ssafy._66days.mono.group.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupAchievement;
import com.ssafy._66days.mono.group.model.entity.GroupAchievementId;

public interface GroupAchievementRepository extends JpaRepository<GroupAchievement, GroupAchievementId> {
	List<GroupAchievement> findByGroup(Group group);
}
