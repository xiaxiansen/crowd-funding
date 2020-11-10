package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: Crowd-funding -- com.atguigu.crowd
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-31 13:07
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CrowdAuthMainClass {
    public static void main(String[] args) {
            SpringApplication.run(CrowdAuthMainClass.class, args);
    }
}
