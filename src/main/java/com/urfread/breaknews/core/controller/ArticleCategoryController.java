package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.entity.ArticleCategory;
import com.urfread.breaknews.core.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class ArticleCategoryController {

    private final ArticleCategoryService articleCategoryService;

    @Autowired
    public ArticleCategoryController(ArticleCategoryService articleCategoryService) {
        this.articleCategoryService = articleCategoryService;
    }

    /**
     * 查询所有分类
     */
    @GetMapping("/find")
    public ResultData<List<ArticleCategory>> findAllCategories(HttpServletRequest req) {
        // 获取当前请求的用户 UID
        String uid = (String) req.getAttribute("uid");
        // 根据 UID 查询该用户的分类
        List<ArticleCategory> categories = articleCategoryService.findAllCategoriesByUid(uid);
        return ResultData.success(categories);
    }
    @PostMapping("/create")
    public ResultData<ArticleCategory> createCategory(@RequestBody ArticleCategory category,HttpServletRequest req) {
        category.setUid(Integer.parseInt((String)req.getAttribute("uid")));
        ArticleCategory createdCategory = articleCategoryService.createCategory(category);
        return ResultData.success(createdCategory);
    }
    @PutMapping("/update")
    public ResultData<ArticleCategory> updateCategory(@RequestBody ArticleCategory category,HttpServletRequest req) {
        category.setUid(Integer.parseInt((String)req.getAttribute("uid")));
        ArticleCategory updatedCategory = articleCategoryService.updateCategory(category);
        return ResultData.success(updatedCategory);
    }
    @DeleteMapping("/delete/{id}")
    public ResultData<Void> deleteCategory(@PathVariable("id") Integer id,HttpServletRequest req) {
        articleCategoryService.deleteCategory(id,Integer.parseInt((String)req.getAttribute("uid")));
        return ResultData.success(null);
    }
}
