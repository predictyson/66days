package com.ssafy._66days.mono.article.model.service;

import com.ssafy._66days.mono.article.model.repository.ArticleRepository;
import com.ssafy._66days.mono.article.model.repository.CommentRepository;
import com.ssafy._66days.mono.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mono.user.model.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
