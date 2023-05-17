package com.ssafy._66days.mono.article.model.repository;

import com.ssafy._66days.mono.article.model.entity.Article;
import com.ssafy._66days.mono.group.model.entity.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByGroupAndIsDeleted(Group group, boolean isDeleted, Pageable pageable);

//    Optional<Article> findByArticleIdAndUser(Long groupId, UUID userId);


}
