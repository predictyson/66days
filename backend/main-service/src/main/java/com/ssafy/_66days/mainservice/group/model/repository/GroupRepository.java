package com.ssafy._66days.mainservice.group.model.repository;

import com.ssafy._66days.mainservice.group.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long groupId);

    List<Group> findAllByGroupNameContains(String searchContent);

    Group findByOwnerId(String searchContent);

    List<Group> findAllByGroupNameContainsOrOwnerId(String searchContent, UUID ownerId);
}
