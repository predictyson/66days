package com.ssafy._66days.article.model.repository;

import com.ssafy._66days.article.model.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    Optional<GroupMember> findByGroupIdAndUserId(Long groupId, UUID userId);
}
