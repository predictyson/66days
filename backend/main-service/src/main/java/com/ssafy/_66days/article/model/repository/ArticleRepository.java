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
//    Optional<Article> findById(Long articleId);
    // 작성한 게시글 저장 함수
    Article save(Article article);

    // 최근 게시글 3개를 가져오는 메소드
    // 이상함 수정 필요
    @Query(value = "SELECT * FROM article WHERE  group_id = :groupId AND is_deleted = 0 ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Article> findRecentArticlesByGroupId(@Param("groupId") Long groupId, @Param("offset") Long offset, @Param("limit") int limit);

    Optional<Article> findByArticleIdAndUser(Long groupId, UUID userId);


}
