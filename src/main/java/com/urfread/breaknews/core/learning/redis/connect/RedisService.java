package com.urfread.breaknews.core.learning.redis.connect;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {

    // 注入 StringRedisTemplate
    @Resource
    private StringRedisTemplate redisTemplate;

    // 保存数据到 Redis
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 从 Redis 获取数据
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除指定的键
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
