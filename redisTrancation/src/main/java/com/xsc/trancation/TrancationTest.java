package com.xsc.trancation;

import com.xsc.jedis.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/22 0:25
 */
public class TrancationTest {
    public static void main(String[] args) {
        new RedisPool().execute(jedis -> {
            Integer money = new TrancationTest().setMoney(jedis, "xsc", 1000);
            System.out.println(money);
        });
    }

    /**
     * @param jedis jedis客户端
     * @param key   key
     * @param money value
     * @return
     */
    private Integer setMoney(Jedis jedis, String key, Integer money) {
        while (true) {
            //监控key
            jedis.watch(key);
            int reMoney = Integer.parseInt(jedis.get(key) + money);
            //开启事务
            Transaction transaction = jedis.multi();
            transaction.set(key, String.valueOf(reMoney));
            //提交事务
            List<Object> exec = transaction.exec();
            if (exec != null) {
                break;
            }
        }
        return Integer.parseInt(jedis.get(key));
    }
}
