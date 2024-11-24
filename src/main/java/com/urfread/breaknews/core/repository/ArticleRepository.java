package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByUidAndDeleted(Integer uid, boolean deleted);
    Optional<Article> findByIdAndDeleted(Integer id, boolean deleted);
}
