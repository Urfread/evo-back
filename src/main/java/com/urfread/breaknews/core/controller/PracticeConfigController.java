package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.entity.PracticeConfig;
import com.urfread.breaknews.core.service.PracticeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/practice/config")
public class PracticeConfigController {

    private final PracticeConfigService practiceConfigService;

    @Autowired
    public PracticeConfigController(PracticeConfigService practiceConfigService) {
        this.practiceConfigService = practiceConfigService;
    }

    /**
     * 查询用户的所有未删除的练习配置
     */
    @GetMapping("/find/{id}")
    public ResultData<PracticeConfig> findPracticeConfig(@PathVariable Long id) {
        return ResultData.success(practiceConfigService.findPracticeConfig(id));
    }

    /**
     * 创建新的练习配置
     */
    @PostMapping("/create")
    public ResultData<PracticeConfig> createConfig(@RequestBody PracticeConfig practiceConfig, HttpServletRequest req) {
        // Ensure UID is set correctly as Long
        practiceConfig.setUid(Long.parseLong((String) req.getAttribute("uid")));
        PracticeConfig createdConfig = practiceConfigService.createConfig(practiceConfig);
        return ResultData.success(createdConfig);
    }
}
