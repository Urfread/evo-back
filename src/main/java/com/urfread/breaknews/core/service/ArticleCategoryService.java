package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.ArticleCategory;
import com.urfread.breaknews.core.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleCategoryService {

    private final ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    public ArticleCategoryService(ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    /**
     * 查询所有分类
     */
    public List<ArticleCategory> findAllCategoriesByUid(String uid) {
        return articleCategoryRepository.findByUidAndDeleted(Integer.parseInt(uid), false);
    }
    /**
     * 创建分类
     */
    public ArticleCategory createCategory(ArticleCategory category) {
        category.onCreate();
        return articleCategoryRepository.save(category);
    }
    /**
     * 编辑分类
     */
    public ArticleCategory updateCategory(ArticleCategory category) {
        // 获取数据库中的该分类对象
        Optional<ArticleCategory> existingCategoryOpt = articleCategoryRepository.findByIdAndDeleted(category.getId(),false);

        if (existingCategoryOpt.isPresent()) {
            ArticleCategory existingCategory = existingCategoryOpt.get();

            // 检查数据库中的分类是否属于当前用户
            if (existingCategory.getUid().equals(category.getUid())) {
                // 如果匹配，执行更新操作
                return articleCategoryRepository.save(category);
            } else {
                // 如果不匹配，返回 null 而不是抛出异常
                return null;
            }
        } else {
            // 如果分类不存在，返回 null
            return null;
        }
    }
    /**
     * 删除分类
     */
    public void deleteCategory(Integer id, Integer uid) {
        // 先从数据库中查询该分类
        Optional<ArticleCategory> existingCategoryOpt = articleCategoryRepository.findById(id);

        if (existingCategoryOpt.isPresent()) {
            ArticleCategory existingCategory = existingCategoryOpt.get();

            // 检查数据库中的分类是否属于当前用户
            if (existingCategory.getUid().equals(uid)) {
                // 如果匹配，执行删除操作
                articleCategoryRepository.deleteById(id);
            }
            // 如果不匹配，不执行删除，直接返回
        }
        // 如果分类不存在，不执行删除，直接返回
    }
}
