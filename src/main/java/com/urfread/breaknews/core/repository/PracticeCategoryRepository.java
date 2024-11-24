package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.PracticeCategory;
import com.urfread.breaknews.core.common.entity.PracticeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface PracticeCategoryRepository extends JpaRepository<PracticeCategory, Long> {
    // 根据用户ID查询未删除的练习
    @Query("SELECT p FROM PracticeCategory p WHERE p.uid = :uid AND p.deleted = false")
    List<PracticeCategory> findByUidAndDeletedFalse(@Param("uid") Long uid);

    // 根据用户ID查询所有分类
    List<PracticeCategory> findByUid(Long uid);

    // 根据分类名称查询分类
    List<PracticeCategory> findByCategoryName(String categoryName);

    // 逻辑删除分类，设置 deleted 字段为 true
    @Modifying
    @Transactional
    @Query("UPDATE PracticeCategory c SET c.deleted = true WHERE c.id = :id")
    void softDeleteByIdAndUid(@Param("id")Long id);

    // 更新分类名称
    @Modifying
    @Transactional
    @Query("UPDATE PracticeCategory c SET c.categoryName = :categoryName WHERE c.id = :id")
    void updateCategoryNameById(@Param("id") Long id, @Param("categoryName") String categoryName);
}
