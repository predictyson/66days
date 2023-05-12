package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.dto.requestDto.CommentRequestDTO;
import com.ssafy._66days.article.model.dto.responseDto.ArticleResponseDTO;
import com.ssafy._66days.article.model.dto.requestDto.ArticleRequestDTO;
import com.ssafy._66days.article.model.dto.responseDto.CommentResponseDTO;
import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.article.model.entity.Comment;
import com.ssafy._66days.article.model.repository.CommentRepository;
import com.ssafy._66days.global.util.CheckUserUtil;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.article.model.repository.ArticleRepository;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final String userIdStr = "a817d372-ee0d-11ed-a26b-0242ac110003";
    private final UUID userId = UUID.fromString(userIdStr);
    public ArticleService(
            UserRepository userRepository,
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            ArticleRepository articleRepository,
            CommentRepository commentRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }


    public ArticleResponseDTO createArticle(
            Long groupId,
            ArticleRequestDTO articleRequestDTO
    ) {
        if (articleRequestDTO.getTitle() == null || articleRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("제목과 내용은 필수 입력 항목입니다.");
        }
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));

        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }
        // 유저에게 받은 게시글 정보, 작성일자, user, group 정보등을 담아 객체를 생성한다
        Article articleCreate = Article.builder()
                .title(articleRequestDTO.getTitle())
                .content(articleRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .user(user)
                .group(group)
                .build();
        Article savedArticle = articleRepository.save(articleCreate);   // 생성한 객체를 DB에 저장

        String role = groupMemberRepository.findByUser(user).getAuthority();
        return ArticleResponseDTO.of(savedArticle, role); // 저장한 게시글을 게시글 dto에 담아 반환한다
    }


    public ArticleResponseDTO getArticle(
            Long groupId,
            Long articleId
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));

        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
        String role = groupMemberRepository.findByUser(user).getAuthority();

        return ArticleResponseDTO.of(article, role);
    }


    public List<ArticleResponseDTO> getArticleList(
            Long groupId,
            int offset
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }
        Pageable pageable = PageRequest.of(offset, 3);
        // offset 값과 limit 값을 이용해 최근 게시글 3개를 가져온다
        List<Article> articleList = articleRepository.findByGroupAndIsDeleted(group, false, pageable);
        List<ArticleResponseDTO> articleResponseDTOs = new ArrayList<>();
        // 가져온 게시글을 ArticleDTO 리스트로 변환한다
        for (int i = 0; i < articleList.size(); i++) {
            User articleUser = articleList.get(i).getUser();
            Group articleGroup = articleList.get(i).getGroup();

            String role = groupMemberRepository.findByGroupAndUser(articleGroup, articleUser).get().getAuthority();
            articleResponseDTOs.add(ArticleResponseDTO.of(articleList.get(i), role));
        }
        return articleResponseDTOs;
    }

    public ArticleResponseDTO updateArticle(
            Long groupId,
            Long articleId,
            ArticleRequestDTO articleUpdateRequestDTO
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));

        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);

        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!article.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글이 아닙니다");
        }
        if (article.isDeleted()) {
            throw new IllegalArgumentException("삭제된 글입니다");
        }
        if (articleUpdateRequestDTO.getTitle() != null) {
            throw new IllegalArgumentException("제목은 수정할 수 없습니다");
        }
        if (articleUpdateRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("수정할 내용을 작성해 주세요");
        }

        article.ModifyArticle(articleUpdateRequestDTO.getContent());    // setter쓰지 않고 ModifyArticle함수 이용해 update
        String role = groupMemberRepository.findByGroupAndUser(group, user).get().getAuthority(); // 그룹, 유저 정보로 그룹 내 권한 가져온다
        return ArticleResponseDTO.of(article, role);
    }

    public boolean deleteArticle(
            Long groupId,
            Long articleId
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));

        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);

        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }

        // 게시글을 조회하여 삭제한다
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (!article.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글이 아닙니다");
        }
        article.setDeleted(true);
        articleRepository.save(article);
        return true; // 게시글이 성공적으로 삭제되었으므로 1을 반환한다
    }

    public CommentResponseDTO writeComment(
            Long groupId,
            Long articleId,
            CommentRequestDTO commentRequestDTO
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));

        boolean isUserInGroup = checkUserUtil.isUserInGroup(group, user);

        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에서 탈퇴한 유저입니다");
        }

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));
        if (commentRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("댓글을 작성해주시기 바랍니다");
        }
        Comment comment = Comment.builder()
                .content(commentRequestDTO.getContent())
                .group(Group.builder().groupId(groupId).build())
                .article(article)
                .user(user)
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDTO.of(savedComment);
    }

    public List<CommentResponseDTO> getCommentList(
            Long articleId,
            int offset
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));

        Pageable pageable = PageRequest.of(offset, 10);
        // offset 값과 limit 값을 이용해 최근 댓글 10개를 가져온다
        List<Comment> commentList = commentRepository.findByArticleAndIsDeleted(article, false, pageable);
        List<CommentResponseDTO> commentResponseDTOs = commentList.stream()
                .map(comment -> CommentResponseDTO.from(comment))
                .collect(Collectors.toList());
        return commentResponseDTOs;
    }

    public boolean deleteComment(
            Long articleId,
            Long commentId
    ) {
        CheckUserUtil checkUserUtil = new CheckUserUtil(groupMemberRepository, userService);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
        Comment deleteComment = commentRepository.findByCommentIdAndArticle(commentId, article)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));
        if (deleteComment.getUser() == null || !deleteComment.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 작성한 댓글만 삭제할 수 있습니다");
        }
        deleteComment.setDeleted(true);
        return true;
    }
}
