package com.xsc.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author JakeXsc
 */
public class LettuceConnection {
    public static void main(String[] args) {
        RedisClient lettuce = RedisClient.create("redis://123@192.168.37.128");
        StatefulRedisConnection<String, String> connection = lettuce.connect();
        RedisCommands<String, String> sync = connection.sync();
        sync.set("name", "xsc");
        System.out.println(sync.get("name"));
    }
}
