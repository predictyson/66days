package com.ssafy._66days.article.model.repository;

import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.group.model.entity.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByGroupAndIsDeleted(Group group, boolean isDeleted, Pageable pageable);

//    Optional<Article> findByArticleIdAndUser(Long groupId, UUID userId);


}
