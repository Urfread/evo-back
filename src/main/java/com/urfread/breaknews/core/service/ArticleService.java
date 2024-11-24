package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.Article;
import com.urfread.breaknews.core.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAllArticlesByUid(String uid) {
        return articleRepository.findByUidAndDeleted(Integer.parseInt(uid), false);
    }

    public Article createArticle(Article article) {
        article.onCreate();
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        Optional<Article> existingArticleOpt = articleRepository.findById(article.getId());

        if (existingArticleOpt.isPresent()) {
            Article existingArticle = existingArticleOpt.get();
            if (existingArticle.getUid().equals(article.getUid())) {
                return articleRepository.save(article);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void deleteArticle(Integer id, Integer uid) {
        Optional<Article> existingArticleOpt = articleRepository.findById(id);

        if (existingArticleOpt.isPresent()) {
            Article existingArticle = existingArticleOpt.get();
            if (existingArticle.getUid().equals(uid)) {
                articleRepository.deleteById(id);
            }
        }
    }
}
