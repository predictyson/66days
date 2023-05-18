package com.ssafy._66days.mono.group.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import com.ssafy._66days.mono.group.model.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	Optional<Group> findById(Long groupId);

	Page<Group> findAllByGroupNameContains(String searchContent, PageRequest pageRequest);

	Optional<Group> findByOwnerId(UUID ownerId);

	Page<Group> findAllByGroupNameContainsOrOwnerId(String searchContent, UUID ownerId, PageRequest pageRequest);

	Optional<Group> findByOwnerIdAndGroupId(UUID userId, Long groupId);
}
