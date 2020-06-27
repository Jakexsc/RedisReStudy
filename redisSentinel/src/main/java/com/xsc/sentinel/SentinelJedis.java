package com.xsc.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;


/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/28 0:34
 */
public class SentinelJedis {
    public static void main(String[] args) {
        //配置jedis连接池参数
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000);
        Set<String> sentinels = new HashSet<>();
        //配置哨兵ip端口
        sentinels.add("192.168.37.128:26379");
        //生成哨兵连接池对象
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels, config, "chen-980426");
        Jedis jedis = null;
        while (true) {
            try {
                jedis = sentinelPool.getResource();
                String xsc = jedis.get("xsc");
                System.out.println(xsc);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
                try {
                    //停止5秒 测试哨兵
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
