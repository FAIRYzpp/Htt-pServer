package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;

public class RedisUtils {
    private static String HOST = "10.13.0.103";
    private static int PORT = 6666;
    private static String PASSWORD = "Weibo_IM_QA";

    //最大连接数
    private static int MAX_TOTLE = 2000;
    //控制一个pool最多有多少个状态为idle的jedis实例
    private static int MAX_IDLE = 1000;
    //等待可用连接的最大时间
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    //初始化redis连接池
    static {
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTLE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,HOST,PORT,TIMEOUT,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取redis实例
    public static synchronized Jedis getJedis() {
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jedis;
    }

    public synchronized void returnResource(Jedis jedis){
        if (jedis != null)
            jedisPool.returnResource(jedis);
    }

    public synchronized void returnBrokenResource(Jedis jedis){
        if (jedis != null)
            jedisPool.returnBrokenResource(jedis);
    }
}
