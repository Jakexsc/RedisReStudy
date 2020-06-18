package com.xsc.jedis;

public class JedisPoolTest {
    public static void main(String[] args) {
        RedisPool redisPool = new RedisPool();
        redisPool.execute(jedis -> {
            System.out.println(jedis.ping());
        });
    }
}
