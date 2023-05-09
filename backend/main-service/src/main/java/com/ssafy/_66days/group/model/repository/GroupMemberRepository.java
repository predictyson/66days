package com.ssafy._66days.group.model.repository;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.entity.GroupMemberId;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
    Optional<GroupMember> findByGroupAndUser(Group group, User user);
}

