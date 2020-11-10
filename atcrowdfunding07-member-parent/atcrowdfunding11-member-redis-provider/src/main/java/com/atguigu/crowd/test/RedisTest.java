package com.atguigu.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: Crowd-funding -- PACKAGE_NAME
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-30 21:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void testSet(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("redis", "重庆森林");
        String s = operations.get("redis");
        System.out.println("s="+s);
    }
}
