package com.urfread.breaknews.core.repository;

import com.urfread.breaknews.core.common.entity.PracticeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeConfigRepository extends JpaRepository<PracticeConfig, Long> {

    // 根据用户UID查询所有未删除的练习配置
    List<PracticeConfig> findByUidAndDeletedFalse(Long uid);

    // 根据练习ID查询练习配置
    Optional<PracticeConfig> findByPracticeId(Long practiceId);

    // 逻辑删除配置，设置 deleted 字段为 true
    @Modifying
    @Transactional
    @Query("UPDATE PracticeConfig p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(@Param("id")Long id);
}
