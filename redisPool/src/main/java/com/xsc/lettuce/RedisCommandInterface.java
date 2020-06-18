package com.xsc.lettuce;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;

import java.util.List;

/**
 * @author JakeXsc
 */
public interface RedisCommandInterface extends Commands {
    /**
     * redis令牌桶测试
     *
     * @param key    key
     * @param init   桶容量
     * @param count  最多出现几次
     * @param period 时间
     * @param quota  出现的次数
     * @return List<Object>
     */
    @Command("CL.THROTTLE ?0 ?1 ?2 ?3 ?4")
    List<Object> throttle(String key, Long init, Long count, Long period, Long quota);
}
