package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program: Crowd-funding -- com.atguigu.crowd
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-31 13:26
 */
@EnableZuulProxy
@SpringBootApplication
public class CrowdZuulMainClass {
    public static void main(String[] args) {
            SpringApplication.run(CrowdZuulMainClass.class, args);
    }
}
