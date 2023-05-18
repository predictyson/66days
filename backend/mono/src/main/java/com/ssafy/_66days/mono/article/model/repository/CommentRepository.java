package com.ssafy._66days.mono.article.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.ssafy._66days.mono.article.model.entity.Article;
import com.ssafy._66days.mono.article.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByArticleAndIsDeleted(Article article, boolean isDeleted, Pageable pageable);

	Optional<Comment> findByCommentIdAndArticle(Long commentId, Article article);
}
