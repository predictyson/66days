package com.ssafy._66days.Article.model.repository;

import com.ssafy._66days.Article.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
