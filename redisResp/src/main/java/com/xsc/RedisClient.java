package com.xsc;

import java.io.IOException;
import java.net.Socket;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/19 0:04
 */
public class RedisClient {
    private Socket socket;

    public RedisClient() {
        try {
            socket = new Socket("192.168.37.128", 6379);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接失败!");
        }
    }

    /**
     * 执行 Redis Set命令
     *
     * @param key
     * @param value
     * @return String
     * @throws IOException
     */
    public String set(String key, String value) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //数组长度为3
        stringBuilder.append("*3")
                .append("\r\n")
                .append("$")
                .append("set".length())
                .append("\r\n")
                //set的命令
                .append("set")
                .append("\r\n")
                //key的长度
                .append("$")
                .append(key.getBytes().length)
                .append("\r\n")
                //key值
                .append(key)
                .append("\r\n")
                //value的长度
                .append("$")
                .append(value.getBytes().length)
                .append("\r\n")
                //value值
                .append(value)
                .append("\r\n");
        socket.getOutputStream().write(stringBuilder.toString().getBytes());
        byte[] b = new byte[1024];
        socket.getInputStream().read(b);
        System.out.println(stringBuilder.toString());
        return new String(b);
    }

    /**
     * 执行 Redis get命令
     *
     * @param key
     * @return String
     * @throws IOException
     */
    public String get(String key) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //数组长度
        stringBuilder.append("*2")
                .append("\r\n")
                .append("$")
                .append("get".length())
                .append("\r\n")
                //get操作
                .append("get")
                .append("\r\n")
                //key长度
                .append("$")
                .append(key.getBytes().length)
                .append("\r\n")
                //key值
                .append(key)
                .append("\r\n");
        socket.getOutputStream().write(stringBuilder.toString().getBytes());
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println(stringBuilder.toString());
        return new String(bytes);
    }

    public static void main(String[] args) throws IOException {
        String set = new RedisClient().set("k1", "v1");
        System.out.println(set);
        String k1 = new RedisClient().get("k1");
        System.out.println(k1);
    }
}
