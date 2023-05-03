package com.ssafy._66days.Article.model.repository;

import com.ssafy._66days.Article.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


}
