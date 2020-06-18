package com.xsc.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.RedisCommandFactory;

import java.util.List;

/**
 * @author JakeXsc
 */
public class ThrottleTest {
    private String name;

    public static void main(String[] args) {
        RedisClient lettuce = RedisClient.create("redis://chen-980426@192.168.37.128");
        StatefulRedisConnection<String, String> connection = lettuce.connect();
        RedisCommandFactory redisCommandFactory = new RedisCommandFactory(connection);
        RedisCommandInterface commands = redisCommandFactory.getCommands(RedisCommandInterface.class);
        List<Object> xsc = commands.throttle("Xsc", 10L, 10L, 60L, 1L);
        System.out.println(xsc);
    }

    public Integer testCommend(String name) {
        Integer javaTest = 3;
        return javaTest;
    }

}
