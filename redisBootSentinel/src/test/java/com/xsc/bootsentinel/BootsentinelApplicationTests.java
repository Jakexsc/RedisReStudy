package com.xsc.bootsentinel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class BootsentinelApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        while (true) {
            try {
                //根据key获取value
                String value = redisTemplate.opsForValue().get("xsc");
                System.out.println(value);
            } catch (Exception e) {
                //这里catch不做处理
            } finally {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
