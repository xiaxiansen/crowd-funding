package com.atguigu.handler;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @program: Crowd-funding -- com.atguigu.handler
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-01 20:28
 */
@RestController
public class SessionHandler {
    @RequestMapping("/test/spring/session")
    public String testSession(HttpSession session){
        session.setAttribute("king", "hello-king");
        return "数据存入redis";
    }
}
