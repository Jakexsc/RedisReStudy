package com.xsc.queue;

import com.xsc.jedis.RedisPool;

public class RedisQueueTest {
    public static void main(String[] args) {
        RedisPool redisPool = new RedisPool();
        redisPool.execute(jedis -> {
            DelayMsgQueue delayMsgQueue = new DelayMsgQueue(jedis, "xsc.redis.queue");
            Thread producer = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        delayMsgQueue.producer("xscQueueData");
                    }
                }
            };
            Thread consumer = new Thread() {
                @Override
                public void run() {
                    delayMsgQueue.consumer();
                }
            };
            producer.start();
            consumer.start();
            try {
                Thread.sleep(7000);
                consumer.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
