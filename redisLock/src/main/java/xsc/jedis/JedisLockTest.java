package xsc.jedis;

import redis.clients.jedis.params.SetParams;

/**
 * jedis 分布式锁
 *
 * @author 64919
 */
public class JedisLockTest {
    private static final String OK = "OK";

    public static void main(String[] args) {
        RedisPool redisPool = new RedisPool();
        redisPool.execute(jedis -> {
            //设置键、值和过期时间
            String set = jedis.set("s1", "v1", new SetParams().nx().ex(5));
            //如果获取锁
            if(set != null && OK.equals(set)) {
                jedis.set("name", "xsc");
                System.out.println(jedis.get("name"));
                //释放锁
                jedis.del("s1");
            } else {
                //被占位 停止/暂缓 操作
            }
        });
    }
}
