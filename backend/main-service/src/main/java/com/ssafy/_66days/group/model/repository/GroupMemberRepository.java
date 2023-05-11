package com.ssafy._66days.group.model.repository;

import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.group.model.entity.GroupMember;
import com.ssafy._66days.group.model.entity.GroupMemberId;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
    Optional<GroupMember> findByGroupAndUser(Group group, User user);

    List<GroupMember> findByGroup(Optional<Group> byId);

    List<GroupMember> findByUser(User user);

    List<GroupMember> findAllByUser(User user);

    Long countByUserAndIsDeleted(User user, boolean b);

    Long countByGroupAndAuthority(Group group, String manager);

    Long countByGroupAndAuthorityAndIsDeleted(Group group, String manager, boolean b);

    Long countByGroupAndIsDeleted(Group group, boolean b);
}

