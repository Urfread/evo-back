package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.PracticeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PracticeNoteRepository extends JpaRepository<PracticeNote, Long> {

    // 根据用户ID和类别ID查询未删除的练习
    List<PracticeNote> findByUidAndCategoryIdAndDeletedFalse(Long uid, Long categoryId);

    // 根据用户ID查询未删除的练习
    @Query("SELECT p FROM PracticeNote p WHERE p.uid = :uid AND p.deleted = false")
    List<PracticeNote> findByUidAndDeletedFalse(@Param("uid") Long uid);

    // 根据类别ID查询未删除的练习
    List<PracticeNote> findByCategoryIdAndDeletedFalse(Long categoryId);

    // 查询所有未删除的练习
    List<PracticeNote> findByDeletedFalse();

    // 根据ID查询未删除的练习
    Optional<PracticeNote> findByIdAndDeletedFalse(Long id);
    List<PracticeNote> findByUidAndDeleted(Long uid, Boolean deleted);
    // 假删除（更新 deleted 字段为 true）
    @Modifying
    @Transactional
    @Query("UPDATE PracticeNote p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(@Param("id") Long id);  // 使用 @Param 来绑定 id 参数
}
