package xsc.jedis;

import io.rebloom.client.Client;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * 布隆过滤器
 *
 * @author 64919
 */
public class BloomTest {
    public static void main(String[] args) {
        GenericObjectPoolConfig config;
        config = new GenericObjectPoolConfig();
        config.setMaxIdle(300);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(config, "192.168.37.128", 6379, 30000, "chen-980426");
        Client client = new Client(jedisPool);
//        for (int i = 0; i < 10000; i++) {
//            client.add("bloom", "bloomTest---" + i);
//        }
        boolean bloom = client.exists("bloom", "bloomTest---10000");
        System.out.println(bloom);
    }
}
