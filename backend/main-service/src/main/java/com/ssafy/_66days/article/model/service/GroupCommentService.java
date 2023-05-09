package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.dto.responseDto.CommentResponseDTO;
import com.ssafy._66days.article.model.dto.requestDto.CommentRequestDTO;
import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.global.method.CheckUser;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.article.model.entity.Comment;
import com.ssafy._66days.article.model.repository.ArticleRepository;
import com.ssafy._66days.article.model.repository.CommentRepository;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final CommentRepository commentRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserService userService;
    public GroupCommentService(
            ArticleRepository articleRepository,
            CommentRepository commentRepository,
            GroupMemberRepository groupMemberRepository,
            UserService userService) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userService = userService;
    }

//    public CommentResponseDTO writeComment(
//            UserDetails currentUser,
//            Long groupId,
//            Long articleId,
//            CommentRequestDTO commentRequestDTO
//            ) {
//        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
//        UUID userId = checkUser.isExistUser(currentUser);
//        if (userId == null) {
//            throw new IllegalArgumentException("존재하지 않는 유저입니다");
//        }
//
//        int isUSerInGroup = checkUser.isUserInGroup(groupId, userId);
//        if (isUSerInGroup == 0) {
//            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
//        }
//
//        Article article = articleRepository.findById(articleId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));
//        if (commentRequestDTO.getContent() == null) {
//            throw new IllegalArgumentException("댓글을 작성해주시기 바랍니다");
//        }
//        Comment comment = Comment.builder()
//                .content(commentRequestDTO.getContent())
//                .group(Group.builder().groupId(groupId).build())
//                .article(article)
//                .user(User.builder().userId(userId).build())
//                .createdAt(LocalDateTime.now())
//                .isDeleted(0)
//                .build();
//
//        Comment savedComment = commentRepository.save(comment);
//        return CommentResponseDTO.of(savedComment);
//    }
//
//    public List<CommentResponseDTO> findComments(
//            Long articleId,
//            Long offset
//    ) {
//        articleRepository.findById(articleId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));
//
//        List<Comment> comments = commentRepository.findRecentCommnentsByArticle(articleId, offset, 10);
//        List<CommentResponseDTO> commentResponseDTOS = comments.stream()
//                .map(comment -> CommentResponseDTO.from(comment))
//                .collect(Collectors.toList());
//
//        return commentResponseDTOS;
//    }
//
//    public Integer deleteComment(
//        Long articleId,
//        Long commentId
//    ) {
//        Comment deleteComment = commentRepository.findByCommentIdAndArticleId(commentId, articleId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));
//
//        deleteComment.setIsDeleted(1);
//        return 1;
//    }
}
