package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class MybatisTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberPOMapper memberPOMapper;
    public static final Logger LOGGER = LoggerFactory.getLogger(MybatisTest.class);
    @Test
    public void testMapper(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String source = "123123";
        String encode = passwordEncoder.encode(source);
        MemberPO memberPO = new MemberPO(null, "jack", encode, "杰克", "kack@qq.com", 1, 1, "杰克", "123123", 2);
        memberPOMapper.insert(memberPO);
    }
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        LOGGER.info(connection.toString());
    }
}
