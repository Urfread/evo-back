package com.urfread.breaknews.core.learning.redis.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RedisTestComponent {

    @Resource
    private RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(RedisTestComponent.class);

    @PostConstruct
    public void init() {
        // 使用 RedisService 进行 Redis 操作
        redisService.setValue("hello", "world");
        String value = redisService.getValue("hello");
        logger.info("从 Redis 获取的值: {}", value);  // 使用日志记录，应该输出 "world"

        // 删除键
        redisService.deleteValue("hello");
        String deletedValue = redisService.getValue("hello");
        logger.info("删除后的值: {}", deletedValue);  // 使用日志记录，应该输出 null
    }
}
