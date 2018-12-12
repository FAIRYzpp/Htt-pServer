package com.redis;

import org.junit.Test;

public class RedisUtilsTest {
    @Test
    public void testRedisPool(){
        for (int i=1;i<=RedisUtils.getJedis().hlen("user_id");i++){
            String uid = RedisUtils.getJedis().hget("user_id", String.valueOf(i));
            System.out.println(uid);
        }
    }

}
