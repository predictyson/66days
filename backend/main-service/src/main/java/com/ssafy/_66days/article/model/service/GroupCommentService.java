package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.dto.GroupCommentDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.GroupCommentRequestDTO;
import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.article.model.entity.Group;
import com.ssafy._66days.article.model.entity.GroupComment;
import com.ssafy._66days.article.model.entity.user.User;
import com.ssafy._66days.article.model.repository.ArticleRepository;
import com.ssafy._66days.article.model.repository.GroupCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("commentService")
@Transactional
public class GroupCommentService {
    private final ArticleRepository articleRepository;
    private final GroupCommentRepository groupCommentRepository;

    public GroupCommentService(
            ArticleRepository articleRepository,
            GroupCommentRepository groupCommentRepository) {
        this.articleRepository = articleRepository;
        this.groupCommentRepository = groupCommentRepository;
    }

    public GroupCommentDTO writeComment(
            Long articleId,
            GroupCommentRequestDTO groupCommentRequestDTO,
            UUID userUuid,
            Long groupId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));
        if (groupCommentRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("댓글을 작성해주시기 바랍니다");
        }
        GroupComment groupComment = GroupComment.builder()
                .content(groupCommentRequestDTO.getContent())
                .group(Group.builder().groupId(groupId).build())
                .article(article)
                .user(User.builder().userId(userUuid).build())
                .createdAt(LocalDateTime.now())
                .isDeleted(0)
                .build();

        GroupComment savedComment = groupCommentRepository.save(groupComment);
        return GroupCommentDTO.of(savedComment);
    }

    public List<GroupCommentDTO> findComments(
            Long articleId,
            Long offset
    ) {
        articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));

        List<GroupComment> comments = groupCommentRepository.findRecentCommnentsByArticle(articleId, offset, 10);
        List<GroupCommentDTO> groupCommentDTOs = comments.stream()
                .map(comment -> GroupCommentDTO.from(comment))
                .collect(Collectors.toList());

        return groupCommentDTOs;
    }

    public Integer deleteComment(
        Long articleId,
        Long commentId
    ) {
        GroupComment deleteComment = groupCommentRepository.findByCommentIdAndArticleId(commentId, articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));

        deleteComment.setIsDeleted(1);
        return 1;
    }
}
