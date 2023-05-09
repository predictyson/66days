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
}
