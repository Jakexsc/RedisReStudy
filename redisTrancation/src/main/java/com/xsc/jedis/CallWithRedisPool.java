package com.xsc.jedis;

import redis.clients.jedis.Jedis;

/**
 * 连接池接口
 *
 * @author JakeXsc
 */
public interface CallWithRedisPool {
    /**
     * jedis调用的接口方法
     *
     * @param jedis jedis客户端
     */
    void call(Jedis jedis);
}
