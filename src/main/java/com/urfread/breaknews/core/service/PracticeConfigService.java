package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.PracticeConfig;
import com.urfread.breaknews.core.repository.PracticeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PracticeConfigService {

    private final PracticeConfigRepository practiceConfigRepository;

    @Autowired
    public PracticeConfigService(PracticeConfigRepository practiceConfigRepository) {
        this.practiceConfigRepository = practiceConfigRepository;
    }
    /**
     * 创建新的练习配置
     *
     * @param practiceConfig 练习配置对象
     * @return 新创建的练习配置
     */
    public PracticeConfig createConfig(PracticeConfig practiceConfig) {
        return practiceConfigRepository.save(practiceConfig);
    }

    public PracticeConfig findPracticeConfig(Long id) {
        return practiceConfigRepository.findByPracticeId(id).orElseGet(null);
    }
}
