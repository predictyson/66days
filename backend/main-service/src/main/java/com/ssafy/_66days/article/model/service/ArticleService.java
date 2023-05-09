package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.dto.requestDto.CommentRequestDTO;
import com.ssafy._66days.article.model.dto.responseDto.ArticleResponseDTO;
import com.ssafy._66days.article.model.dto.requestDto.ArticleRequestDTO;
import com.ssafy._66days.article.model.dto.responseDto.CommentResponseDTO;
import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.article.model.entity.Comment;
import com.ssafy._66days.article.model.repository.CommentRepository;
import com.ssafy._66days.global.method.CheckUser;
import com.ssafy._66days.group.model.entity.Group;
import com.ssafy._66days.article.model.repository.ArticleRepository;
import com.ssafy._66days.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.group.model.repository.GroupRepository;
import com.ssafy._66days.user.model.repository.UserRepository;
import com.ssafy._66days.user.model.entity.User;
import com.ssafy._66days.user.model.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("articleService")
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
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);

        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
        }

        // userId로 user 객체를 받아온다
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        // groupId로 그룹 객체를 받아온다
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다"));
        // 유저에게 받은 게시글 정보, 작성일자, user, group 정보등을 담아 객체를 생성한다
        Article articleCreate = Article.builder()
                .title(articleRequestDTO.getTitle())
                .content(articleRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .isDeleted(0)
                .user(user)
                .group(group)
                .build();
        // DB 저장하는 함수 호출
        Article savedArticle = articleRepository.save(articleCreate);
        // 저장한 게시글을 게시글 dto에 담아 반환한다
        return ArticleResponseDTO.of(savedArticle);
    }

    public ArticleResponseDTO getArticle(
            Long groupId,
            Long articleId
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
        }
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return ArticleResponseDTO.of(article);
        } else {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다");
        }
    }
    public List<ArticleResponseDTO> getThreeArticles(
            Long groupId,
            Long offset
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);

        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
        }
        // offset 값과 limit 값을 이용해 최근 게시글 3개를 가져온다
        List<Article> articles = articleRepository.findRecentArticlesByGroupId(groupId, offset, 3);

        // 가져온 게시글을 ArticleDTO 리스트로 변환한다
        List<ArticleResponseDTO> articleResponseDTOs = articles.stream()
                .map(article -> ArticleResponseDTO.from(article))
                .collect(Collectors.toList());

        return articleResponseDTOs;
    }

    public ArticleResponseDTO updateArticle(
            Long groupId,
            Long articleId,
            ArticleRequestDTO articleUpdateRequestDTO
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
        }

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (!article.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글이 아닙니다");
        }
        if (articleUpdateRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("수정할 내용을 작성해 주세요");
        }

        article.setContent(articleUpdateRequestDTO.getContent());
        Article updatedArticle = articleRepository.save(article);

        return ArticleResponseDTO.of(updatedArticle);
    }

    public int deleteArticle(
            Long groupId,
            Long articleId
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);
        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
        }

        // 게시글을 조회하여 삭제한다
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (!article.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글이 아닙니다");
        } else {
            article.setIsDeleted(1);
            articleRepository.save(article);
            return 1; // 게시글이 성공적으로 삭제되었으므로 1을 반환한다
        }
    }

    public CommentResponseDTO writeComment(
            Long groupId,
            Long articleId,
            CommentRequestDTO commentRequestDTO
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Group groupID = groupRepository.findById(groupId).get();
        User userID = userRepository.findById(userId).get();

        boolean isUserInGroup = checkUser.isUserInGroup(groupID, userID);

        if (!isUserInGroup) {
            throw new IllegalArgumentException("그룹에 속하지 않은 유저입니다");
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
                .user(User.builder().userId(userId).build())
                .createdAt(LocalDateTime.now())
                .isDeleted(0)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDTO.of(savedComment);
    }

    public List<CommentResponseDTO> getCommentsList(
            Long articleId,
            Long offset
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다"));

        List<Comment> comments = commentRepository.findCommentsByArticleId(articleId, offset, 10);
        List<CommentResponseDTO> commentResponseDTOs = comments.stream()
                .map(comment -> CommentResponseDTO.from(comment))
                .collect(Collectors.toList());

        return commentResponseDTOs;
    }

    public Integer deleteComment(
            Long articleId,
            Long commentId
    ) {
        CheckUser checkUser = new CheckUser(groupMemberRepository, userService);
        Article articleID = articleRepository.findById(articleId).get();
        Comment deleteComment = commentRepository.findByCommentIdAndArticle(commentId, articleID)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));
        if (!deleteComment.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 작성한 댓글만 삭제할 수 있습니다");
        }
        deleteComment.setIsDeleted(1);
        return 1;
    }
}
