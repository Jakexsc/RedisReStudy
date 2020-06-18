package com.xsc.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class DelayMsgQueue {
    private Jedis jedis;
    private String queue;

    public DelayMsgQueue(Jedis jedis, String queue) {
        this.jedis = jedis;
        this.queue = queue;
    }

    public void producer(Object data) {
        RedisQueueMsg redisQueueMsg = new RedisQueueMsg();
        redisQueueMsg.setId(UUID.randomUUID().toString());
        redisQueueMsg.setData(data);
        try {
            String s = new ObjectMapper().writeValueAsString(redisQueueMsg);
            System.out.println("msg producer: "  + new Date());
            jedis.zadd(queue, System.currentTimeMillis() + 5000, s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void consumer() {
        while (!Thread.interrupted()) {
            Set<String> zRange = jedis.zrangeByScore(queue, 0, System.currentTimeMillis(), 0, 1);
            if (zRange.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String next = zRange.iterator().next();
            if (jedis.zrem(queue, next) > 0) {
                try {
                    RedisQueueMsg redisQueueMsg = new ObjectMapper().readValue(next, RedisQueueMsg.class);
                    System.out.println("msg consumer:" + redisQueueMsg + new Date());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
