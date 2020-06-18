package xsc.jedis;

import redis.clients.jedis.params.SetParams;

public class JedisPoolTest {
    public static void main(String[] args) {
        RedisPool redisPool = new RedisPool();
        redisPool.execute(jedis -> {
            String set = jedis.set("s1", "v1", new SetParams().nx().ex(5));
            if(set != null && "OK".equals(set)) {
                jedis.set("name", "xsc");
                System.out.println(jedis.get("name"));
                jedis.del("s1");
            } else {

            }
        });
    }
}
