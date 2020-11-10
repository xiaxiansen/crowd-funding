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
    @RequestMapping("/test/spring/get/session")
    public String testSession(HttpSession session){
        String result = (String) session.getAttribute("king");
        return result;
    }
}
