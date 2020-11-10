package com.atguigu.crowd.mvc.handle;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-27 16:33
 */
@Controller
public class testssm {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/test/ssm.html")
    public String testssm(Model model){
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("adminList",adminList);
        String s = null;
        System.out.println(s.length());
//        int i = 10/ 0;
        return "target";
    }
}
