package com.ssafy._66days.article.model.repository;

import com.ssafy._66days.article.model.entity.GroupComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupCommentRepository extends JpaRepository<GroupComment, Long> {
    GroupComment save(GroupComment groupComment);

    @Query(value = "SELECT * FROM group_comment WHERE article_id = :articleId AND is_deleted = 0 ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<GroupComment> findRecentCommnentsByArticle(@Param("articleId") Long articleId, @Param("offset") Long offset, @Param("limit") int limit);

    Optional<GroupComment> findByCommentIdAndArticleId(Long commentId, Long articleId);
}
