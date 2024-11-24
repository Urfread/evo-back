package com.urfread.breaknews.core.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.urfread.breaknews.core.common.entity.PracticeConfig;
import com.urfread.breaknews.core.repository.PracticeConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PracticeConfigRepositoryTest {

    @Autowired
    private PracticeConfigRepository practiceConfigRepository;

    private PracticeConfig practiceConfig;

    @BeforeEach
    public void setUp() {
        // 首先检查是否已经存在 practiceId 为 101 的配置
        Optional<PracticeConfig> existingConfig = practiceConfigRepository.findByPracticeId(101L);

        // 如果配置已经存在，则不插入新配置，直接更新它（如果需要）
        if (existingConfig.isPresent()) {
            practiceConfig = existingConfig.get();
            practiceConfig.setProficiency(5);
            practiceConfig.setDifficulty(3);
            practiceConfig.setImportance(4);
            practiceConfig.setMastered(false); // 默认未掌握
        } else {
            // 初始化测试数据并保存
            practiceConfig = PracticeConfig.builder()
                    .uid(1L)
                    .practiceId(101L)
                    .proficiency(5)
                    .difficulty(3)
                    .importance(4)
                    .mastered(false)  // 默认未掌握
                    .build();
            practiceConfig.onCreate(); // 设置创建时间等字段
        }

        // 保存数据（如果是新数据，则保存；如果是更新的数据，则进行保存）
        practiceConfig = practiceConfigRepository.save(practiceConfig);
    }

    @Test
    public void testCreate() {
        // 测试保存（增）
        assertNotNull(practiceConfig.getId(), "Practice config should be saved and have an ID.");
    }

    @Test
    public void testRead() {
        // 测试读取（查）
        Optional<PracticeConfig> retrievedConfig = practiceConfigRepository.findById(practiceConfig.getId());
        assertTrue(retrievedConfig.isPresent(), "Practice config should be retrieved.");
        assertEquals(practiceConfig.getPracticeId(), retrievedConfig.get().getPracticeId(), "Practice ID should match.");
    }

    @Test
    public void testFindByUidAndDeletedFalse() {
        // 测试根据 UID 查询所有未删除的练习配置
        List<PracticeConfig> configs = practiceConfigRepository.findByUidAndDeletedFalse(1L);
        System.out.println(configs);
        assertFalse(configs.isEmpty(), "There should be at least one practice config for this UID.");
        assertEquals(1L, configs.get(0).getUid(), "The UID of the config should match.");
        assertFalse(configs.get(0).getDeleted(), "The practice config should not be deleted.");
    }

    @Test
    public void testFindByPracticeId() {
        // 测试根据练习 ID 查询练习配置
        Optional<PracticeConfig> retrievedConfig = practiceConfigRepository.findByPracticeId(101L);
        assertTrue(retrievedConfig.isPresent(), "Practice config should be found by practice ID.");
        assertEquals(101L, retrievedConfig.get().getPracticeId(), "Practice ID should match.");
    }

    @Test
    public void testUpdate() {
        // 测试更新（改）
        practiceConfig.setProficiency(4);
        practiceConfig.setDifficulty(2);
        practiceConfig = practiceConfigRepository.save(practiceConfig);  // 更新实体

        Optional<PracticeConfig> updatedConfig = practiceConfigRepository.findById(practiceConfig.getId());
        assertTrue(updatedConfig.isPresent(), "Updated practice config should exist.");
        assertEquals(4, updatedConfig.get().getProficiency(), "Proficiency should be updated.");
        assertEquals(2, updatedConfig.get().getDifficulty(), "Difficulty should be updated.");
    }

    @Test
    @Transactional
    public void testLogicalDelete() {
        // 测试逻辑删除（设置 deleted 为 true）
        Long configId = practiceConfig.getId();
        System.out.println(practiceConfig);
        practiceConfigRepository.softDeleteById(configId);  // 逻辑删除操作

//        Optional<PracticeConfig> logicallyDeletedConfig = practiceConfigRepository.findById(configId);
//        System.out.println(logicallyDeletedConfig.get());
        //assertTrue(logicallyDeletedConfig.isPresent(), "Practice config should still be found.");
        //assertTrue(logicallyDeletedConfig.get().getDeleted(), "Practice config should be marked as deleted.");
    }
}
