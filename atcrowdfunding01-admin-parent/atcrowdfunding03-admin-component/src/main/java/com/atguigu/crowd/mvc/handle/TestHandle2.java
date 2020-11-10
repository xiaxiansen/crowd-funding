package com.atguigu.crowd.mvc.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-30 15:31
 */
@Controller
public class TestHandle2 {
    @ResponseBody
    @RequestMapping("/test/ajax.html")
    public String testAjax() throws InterruptedException {
        Thread.sleep(5000);
        return "success";
    }
}
