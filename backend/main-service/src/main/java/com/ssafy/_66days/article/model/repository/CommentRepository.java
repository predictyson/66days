package com.ssafy._66days.article.model.repository;

import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.article.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);

    // offset 쿼리 수정 필요
    @Query(value = "SELECT * FROM group_comment WHERE article_id = :articleId AND is_deleted = 0 ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Comment> findCommentsByArticleId(@Param("articleId") Long articleId, @Param("offset") Long offset, @Param("limit") int limit);

    Optional<Comment> findByCommentIdAndArticle(Long commentId, Article articleId);
}
