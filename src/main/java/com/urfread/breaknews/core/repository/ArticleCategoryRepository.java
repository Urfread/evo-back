package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
    // 查询特定用户的未删除分类
    List<ArticleCategory> findByUidAndDeleted(Integer uid, boolean deleted);

    // 查询特定 ID 且未删除的分类
    Optional<ArticleCategory> findByIdAndDeleted(Integer id, boolean deleted);
}

