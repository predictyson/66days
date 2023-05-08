package com.ssafy._66days.article.model.repository;

import com.ssafy._66days.article.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 작성한 게시글 저장 함수
    Article save(Article article);

    // 최근 게시글 3개를 가져오는 메소드
    @Query(value = "SELECT * FROM article WHERE is_deleted = 0 AND group_id = :groupId ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Article> findRecentArticlesByGroup(@Param("groupId") Long groupId, @Param("offset") Long offset, @Param("limit") int limit);

    Optional<Article> findByArticleIdAndUserId(Long groupId, UUID userId);


}
