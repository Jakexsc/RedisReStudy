package com.xsc.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author JakeXsc
 */
public class RedisPool {
    private JedisPool jedisPool;
    private GenericObjectPoolConfig config;

    public RedisPool() {
        config = new GenericObjectPoolConfig();
        //设置最大连接数
        config.setMaxIdle(300);
        //设置最大空闲数
        config.setMaxTotal(1000);
        //设置最大等待时间
        config.setMaxWaitMillis(10000);
        //在空闲时检查有效性
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, "192.168.37.128", 6379, 10000, "chen-980426");
    }

    void execute(CallWithRedisPool callWithRedisPool) {
        try (Jedis jedis = jedisPool.getResource()) {
            callWithRedisPool.call(jedis);
        }
    }
}
