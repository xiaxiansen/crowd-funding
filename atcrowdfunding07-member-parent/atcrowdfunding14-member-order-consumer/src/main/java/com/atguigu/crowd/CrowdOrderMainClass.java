package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: Crowd-funding -- com.atguigu.crowd
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-05 16:44
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CrowdOrderMainClass {
    public static void main(String[] args) {
            SpringApplication.run(CrowdOrderMainClass.class, args);
    }
}
