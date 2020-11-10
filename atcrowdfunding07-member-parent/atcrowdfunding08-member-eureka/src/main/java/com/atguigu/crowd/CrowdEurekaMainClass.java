package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: Crowd-funding -- com.atguigu.crowd
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-30 17:27
 */
@EnableEurekaServer
@SpringBootApplication
public class CrowdEurekaMainClass {
    public static void main(String[] args) {
            SpringApplication.run(CrowdEurekaMainClass.class, args);
    }
}
