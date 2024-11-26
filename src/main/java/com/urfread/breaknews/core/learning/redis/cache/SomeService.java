package com.urfread.breaknews.core.learning.redis.cache;

import com.urfread.breaknews.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SomeService {

    private static final Logger logger = LoggerFactory.getLogger(SomeService.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    UserRepository userRepository;

    private static final String CACHE_PREFIX = "data:"; // 缓存键的前缀

    public String getDataById(String id) {
        // 先检查 Redis 缓存中是否有数据
        String cacheKey = CACHE_PREFIX + id;
        String cachedData = redisTemplate.opsForValue().get(cacheKey);

        if (cachedData != null) {
            return cachedData;
        }

        // 如果 Redis 中没有数据，则执行查询操作
        logger.info("执行查询操作: {}", id);  // 使用 SLF4J 输出日志
        // 模拟查询数据库的操作

        String data = "数据：" + userRepository.findByUid(7).orElse(null);
        logger.debug(data);
        // 将查询结果存入 Redis 缓存
        redisTemplate.opsForValue().set(cacheKey, data);

        return data;
    }

    // 删除缓存
    public void deleteCacheById(String id) {
        String cacheKey = CACHE_PREFIX + id;
        redisTemplate.delete(cacheKey); // 删除缓存
        logger.info("已删除缓存: {}", id);
    }
}
