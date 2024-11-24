package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.entity.PracticeCategory;
import com.urfread.breaknews.core.service.PracticeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/practice/category")
public class PracticeCategoryController {

    private final PracticeCategoryService practiceCategoryService;

    @Autowired
    public PracticeCategoryController(PracticeCategoryService practiceCategoryService) {
        this.practiceCategoryService = practiceCategoryService;
    }

    /**
     * 查询所有分类
     */
    @GetMapping("/find")
    public ResultData<List<PracticeCategory>> findAllCategories(HttpServletRequest req) {
        // 获取当前请求的用户 UID
        String uid = (String) req.getAttribute("uid");
        // 根据 UID 查询该用户的分类
        List<PracticeCategory> categories = practiceCategoryService.findAllCategoriesByUid(uid);
        return ResultData.success(categories);
    }

    /**
     * 创建新的练习分类
     */
    @PostMapping("/create")
    public ResultData<PracticeCategory> createCategory(@RequestBody PracticeCategory category, HttpServletRequest req) {
        // Ensure UID is set correctly as Long
        category.setUid(Long.parseLong((String) req.getAttribute("uid")));
        PracticeCategory createdCategory = practiceCategoryService.createCategory(category);
        return ResultData.success(createdCategory);
    }

    /**
     * 更新练习分类
     */
    @PutMapping("/update")
    public ResultData<PracticeCategory> updateCategory(@RequestBody PracticeCategory category, HttpServletRequest req) {
        // Ensure UID is set correctly as Long
        category.setUid(Long.parseLong((String) req.getAttribute("uid")));
        PracticeCategory updatedCategory = practiceCategoryService.updateCategory(category);
        return ResultData.success(updatedCategory);
    }

    /**
     * 删除指定ID的分类
     */
    @DeleteMapping("/delete/{id}")
    public ResultData<Void> deleteCategory(@PathVariable("id") Long id) {
        practiceCategoryService.deleteCategory(id);
        return ResultData.success(null);
    }
}
