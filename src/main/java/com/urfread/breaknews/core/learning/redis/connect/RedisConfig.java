package com.urfread.breaknews.core.learning.redis.connect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 创建 Lettuce 连接工厂
        LettuceConnectionFactory factory = new LettuceConnectionFactory("192.168.171.131", 6379);
        factory.setPassword("123456");  // 设置 Redis 密码
        return factory;
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        // 设置序列化方式
        template.setKeySerializer(new StringRedisSerializer()); // key 序列化方式
        template.setValueSerializer(new StringRedisSerializer()); // value 序列化方式
        return template;
    }
}
