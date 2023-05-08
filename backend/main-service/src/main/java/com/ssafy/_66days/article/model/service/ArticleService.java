package com.ssafy._66days.article.model.service;

import com.ssafy._66days.article.model.dto.ArticleDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.ArticleRequestDTO;
import com.ssafy._66days.article.model.dto.RequestDTO.ArticleUpdateDTO;
import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.article.model.entity.Group;
import com.ssafy._66days.article.model.entity.user.User;
import com.ssafy._66days.article.model.repository.ArticleRepository;
import com.ssafy._66days.article.model.repository.GroupMemberRepository;
import com.ssafy._66days.article.model.repository.GroupRepository;
import com.ssafy._66days.article.model.repository.UserRepository;
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
    private final ArticleRepository articleRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    public ArticleService(ArticleRepository articleRepository, GroupRepository groupRepository, UserRepository userRepository, GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.articleRepository = articleRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }
    public ArticleDTO createArticle(ArticleRequestDTO articleRequestDTO, UUID userId, Long groupId) {
        if (articleRequestDTO.getTitle() == null || articleRequestDTO.getContent() == null) {
            throw new IllegalArgumentException("제목과 내용은 필수 입력 항목입니다.");
        }
        // 해당 그룹에 해당 유저가 있는지 확인한다
        groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속하지 않은 유저입니다"));
        // userId로 user 객체를 받아온다
        User user = userRepository.findByUserId(userId)
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
        return ArticleDTO.of(savedArticle);
    }

    public List<ArticleDTO> findArticles(Long groupId, UUID userId, Long offset) {
        // 그룹멤버 테이블에 해당 그룹에 해당 유저가 있는지 확인한다
        groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속하지 않은 유저입니다"));
        // offset 값과 limit 값을 이용해 최근 게시글 3개를 가져온다
        List<Article> articles = articleRepository.findRecentArticlesByGroup(groupId, offset, 3);

        // 가져온 게시글을 ArticleDTO 리스트로 변환한다
        List<ArticleDTO> articleDTOs = articles.stream()
                .map(article -> ArticleDTO.from(article))
                .collect(Collectors.toList());

        return articleDTOs;
    }

    public ArticleDTO updateArticle(Long groupId, Long articleId, UUID userId, ArticleUpdateDTO articleUpdateDTO) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (!article.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인이 작성한 게시글이 아닙니다");
        }
        if (articleUpdateDTO.getContent() == null) {
            throw new IllegalArgumentException("수정할 내용을 작성해 주세요");
        }

        article.setContent(articleUpdateDTO.getContent());
        articleRepository.save(article);

        return ArticleDTO.of(article);
    }

    public int deleteArticle(Long groupId, Long articleId, UUID userId) {
        // 해당 그룹에 해당 유저가 있는지 확인한다
        groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new IllegalArgumentException("그룹에 속하지 않은 유저입니다"));

        // 게시글을 조회하여 삭제한다
        Optional<Article> optionalArticle = articleRepository.findByArticleIdAndUserId(articleId, userId);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setIsDeleted(1);
            articleRepository.save(article);
            return 1; // 게시글이 성공적으로 삭제되었으므로 1을 반환한다
        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다");
        }
    }
}
