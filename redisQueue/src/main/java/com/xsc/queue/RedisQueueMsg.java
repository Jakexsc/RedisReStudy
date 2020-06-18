package com.xsc.queue;

import java.util.Date;

public class RedisQueueMsg {
    private String id;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RedisQueueMsg{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
