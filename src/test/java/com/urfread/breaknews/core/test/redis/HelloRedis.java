package com.urfread.breaknews.core.test.redis;

import redis.clients.jedis.Jedis;
public class HelloRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.171.129",6379);
        jedis.set("hello", "world");
        System.out.println(jedis.get("hello"));
    }
}
