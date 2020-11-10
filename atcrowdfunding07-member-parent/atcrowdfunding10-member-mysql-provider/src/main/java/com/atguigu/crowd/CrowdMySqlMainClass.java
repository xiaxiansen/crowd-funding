package com.atguigu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: Crowd-funding -- com.atguigu.crowd
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-30 20:37
 */
@MapperScan("com.atguigu.crowd.mapper")
@SpringBootApplication
public class CrowdMySqlMainClass {
    public static void main(String[] args) {
            SpringApplication.run(CrowdMySqlMainClass.class, args);
    }
}
