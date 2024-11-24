package com.urfread.breaknews.core.service;

import com.urfread.breaknews.core.common.entity.PracticeConfig;
import com.urfread.breaknews.core.common.entity.PracticeNote;

import com.urfread.breaknews.core.repository.PracticeConfigRepository;
import com.urfread.breaknews.core.repository.PracticeNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class PracticeNoteService {

    private final PracticeNoteRepository practiceNoteRepository;
    private final PracticeConfigRepository practiceConfigRepository;

    @Autowired
    public PracticeNoteService(PracticeNoteRepository practiceNoteRepository, PracticeConfigRepository practiceConfigRepository) {
        this.practiceNoteRepository = practiceNoteRepository;
        this.practiceConfigRepository = practiceConfigRepository;
    }

    public PracticeNote createArticle(PracticeNote practice) {
        practice.onCreate();
        practice=practiceNoteRepository.save(practice);
        if(practiceConfigRepository.findByPracticeId(practice.getId()).isEmpty()){
            // 同步生成配置
            PracticeConfig practiceConfig= PracticeConfig.builder()
                    .practiceId(practice.getId())
                    .uid(practice.getUid())
                    .difficulty(1)
                    .proficiency(1)
                    .importance(1)
                    .mastered(false)
                    .build();
            practiceConfigRepository.save(practiceConfig);
        }
        return practice;
    }

    public List<PracticeNote> findAllPracticesByUid(String uid) {
        return practiceNoteRepository.findByUidAndDeleted(Long.parseLong(uid),false);
    }

    public void deletePractice(Long id) {
        practiceNoteRepository.softDeleteById(id);
    }

    public List<PracticeNote> getAllInOnePracticesByUid(String uid) {
        return practiceNoteRepository.findByUidAndDeleted(Long.parseLong(uid),false);
    }

    public List<PracticeNote> getDailyPracticesByUid(String uid) {
        int[] reviewIntervals = {1, 2, 4, 7, 15, 30, 90};
        // 获取所有未删除的练习
        List<PracticeNote> practices = practiceNoteRepository.findByUidAndDeleted(Long.parseLong(uid), false);
        // TODO:过滤掉已经掌握的练习(暂时不考虑)

        // 过滤掉不在复习计划中的练习

        // 当前时间
        Instant now = Instant.now();

        // 按复习计划过滤练习
        return practices.stream()
                .filter(practice -> {
                    long daysSinceCreated = Duration.between(practice.getCreatedTime(), now).toDays();
                    if(daysSinceCreated==0)return true;
                    for (int interval : reviewIntervals) {
                        if (daysSinceCreated == interval) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
