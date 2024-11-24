package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.PracticeCategory;
import com.urfread.breaknews.core.repository.PracticeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PracticeCategoryService {

    private final PracticeCategoryRepository practiceCategoryRepository;

    @Autowired
    public PracticeCategoryService(PracticeCategoryRepository practiceCategoryRepository) {
        this.practiceCategoryRepository = practiceCategoryRepository;
    }

    /**
     * 根据用户UID查询所有分类
     *
     * @param uid 用户的UID（String类型，在方法内部转换为Long）
     * @return 分类列表
     */
    public List<PracticeCategory> findAllCategoriesByUid(String uid) {
        return practiceCategoryRepository.findByUidAndDeletedFalse(Long.parseLong(uid));
    }

    /**
     * 创建新的练习分类
     *
     * @param category 练习分类对象
     * @return 新创建的练习分类
     */
    public PracticeCategory createCategory(PracticeCategory category) {
        return practiceCategoryRepository.save(category);
    }

    /**
     * 更新练习分类
     *
     * @param category 更新的练习分类对象
     * @return 更新后的练习分类
     */
    public PracticeCategory updateCategory(PracticeCategory category) {
        // 直接调用仓库方法更新分类名称
        practiceCategoryRepository.updateCategoryNameById(category.getId(), category.getCategoryName());

        // 返回更新后的 PracticeCategory 对象
        return practiceCategoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("分类未找到"));
    }

    /**
     * 删除指定ID和UID的练习分类
     *
     * @param id 分类的ID
     */
    @Transactional
    public void deleteCategory(Long id) {
        // 使用事务来删除指定ID和UID的分类
        practiceCategoryRepository.softDeleteByIdAndUid(id);
    }
}
