package com.xsc.jedis;

import redis.clients.jedis.Jedis;

public interface CallWithRedisPool {
    /**
     * 返回一个jedis对象
     *
     * @param jedis jedis客户端
     */
    void call(Jedis jedis);
}
