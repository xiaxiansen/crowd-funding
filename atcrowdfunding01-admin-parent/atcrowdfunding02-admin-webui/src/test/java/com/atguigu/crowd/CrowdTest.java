package com.atguigu.crowd;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 在类上标记必要的注解，Spring整合Junit

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
	private Logger logger = LoggerFactory.getLogger(CrowdTest.class);
	@Autowired
	private DataSource dataSource;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminService adminService;
	@Autowired
    private RoleMapper roleMapper;
	
	@Test
    public void getRole(){
	    for(int i = 0; i < 235; i++){
	        roleMapper.insert(new Role(null,"role"+i));
        }
    }
	@Test
	public void testTx(){
		Admin admin = new Admin(null, "一代宗师", "123456", "2046", "123@qq.com", null);
		adminService.saveAdmin(admin);
	}
	@Test
	public void testlog(){
		Logger logger = LoggerFactory.getLogger(CrowdTest.class);
		// 按照 Debug 级别打印日志 logger.debug(admin.toString());
		logger.debug("hello debug");
	}
	@Test
	public void test01(){
		for(int i = 0; i < 400; i ++){
			Admin admin = new Admin(null, "重庆森林"+i, "123456", "2046"+i, "123@qq.com"+i, null);
			int count = adminMapper.insert(admin);
		}
		// 按照 Debug 级别打印日志 logger.debug(admin.toString());
//		System.out.println("影响行数:"+count);
//		logger.debug("影响行数:"+count);
	}

	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println("connection:"+connection);
	}
}
*/

