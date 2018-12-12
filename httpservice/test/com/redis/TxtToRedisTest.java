package com.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class TxtToRedisTest {

    @Before
    public void setUp() throws Exception {
        Jedis jedis = new Jedis("10.13.0.103");
        jedis.hget("user","id");
    }

    @After
    public void tearDown() throws Exception {
    }
}
