package com.xsc.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/29 0:07
 */
public class RedisCluster {
    public static void main(String[] args) {
        Set<HostAndPort> node = new HashSet<>();
        //添加集群
        //TODO 注意开放端口
        node.add(new HostAndPort("192.168.37.128", 7001));
        node.add(new HostAndPort("192.168.37.128", 7002));
        node.add(new HostAndPort("192.168.37.128", 7003));
        node.add(new HostAndPort("192.168.37.128", 7004));
        node.add(new HostAndPort("192.168.37.128", 7005));
        node.add(new HostAndPort("192.168.37.128", 7006));
        node.add(new HostAndPort("192.168.37.128", 7007));
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxIdle(300);
        //设置最大空闲数
        config.setMaxTotal(1000);
        //设置最大等待时间
        config.setMaxWaitMillis(10000);
        //在空闲时检查有效性
        config.setTestOnBorrow(true);
        JedisCluster jedisCluster = new JedisCluster(node, 15000, 15000, 5, "chen-980426", config);
        String set = jedisCluster.set("xsc", "xscNB");
        System.out.println(set);
        String value = jedisCluster.get("xsc");
        System.out.println(value);
    }
}
