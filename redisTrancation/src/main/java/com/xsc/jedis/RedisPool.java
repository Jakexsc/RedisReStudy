package com.xsc.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 连接池
 *
 * @author Jakexsc
 */
public class RedisPool {
    private JedisPool jedisPool;
    private GenericObjectPoolConfig config;

    public RedisPool() {
        config = new GenericObjectPoolConfig();
        config.setMaxIdle(300);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, "192.168.37.128", 6379, 10000, null);
    }

    public void execute(CallWithRedisPool callWithRedisPool) {
        try (Jedis jedis = jedisPool.getResource()) {
            callWithRedisPool.call(jedis);
        }
    }
}
