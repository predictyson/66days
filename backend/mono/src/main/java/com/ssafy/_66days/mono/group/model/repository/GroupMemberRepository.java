package com.ssafy._66days.mono.group.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupMember;
import com.ssafy._66days.mono.group.model.entity.GroupMemberId;
import com.ssafy._66days.mono.user.model.entity.User;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
	Optional<GroupMember> findByGroupAndUser(Group group, User user);

	List<GroupMember> findByGroup(Optional<Group> byId);

	GroupMember findByUser(User user);

	List<GroupMember> findAllByUser(User user);

	Long countByUserAndIsDeleted(User user, boolean b);

	Long countByGroupAndAuthority(Group group, String manager);

	Long countByGroupAndAuthorityAndIsDeleted(Group group, String manager, boolean b);

	Long countByGroupAndIsDeleted(Group group, boolean b);

	List<GroupMember> findByGroupAndAuthorityNot(Optional<Group> byId, String drop);

	Optional<GroupMember> findByGroupAndUserAndIsDeleted(Group group, User user, boolean state);

	Optional<GroupMember> findByUserAndIsDeleted(User user, boolean b);

	Optional<GroupMember> findByUserAndGroupAndIsDeleted(User user, Group group, boolean state);
}

