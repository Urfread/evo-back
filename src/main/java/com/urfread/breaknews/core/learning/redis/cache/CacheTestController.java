package com.urfread.breaknews.core.learning.redis.cache;

import com.urfread.breaknews.core.learning.log.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class CacheTestController {

    @Resource
    private SomeService someService;

    // 测试缓存获取接口
    @GetMapping("/cache")
    @LogExecutionTime
    public String testCache() {
        // 返回结果，展示缓存是否生效
        return someService.getDataById("1");
    }

    // 删除缓存接口
    @GetMapping("/dele")
    public String deleteCache() {
        // 删除缓存
        someService.deleteCacheById("1");
        return "缓存已删除";
    }
}
