package xsc.jedis;

import redis.clients.jedis.Jedis;

public interface CallWithRedisPool {
    void call(Jedis jedis);
}
