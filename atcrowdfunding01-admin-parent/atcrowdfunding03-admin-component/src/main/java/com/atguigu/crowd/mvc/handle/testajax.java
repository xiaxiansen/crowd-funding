package com.atguigu.crowd.mvc.handle;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.util.ResultEntity;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-27 18:28
 */
@Controller
public class testajax {
    private Logger logger = LoggerFactory.getLogger(testajax.class);
    @ResponseBody
    @RequestMapping("/requestbody/one.json")
    public ResultEntity<Admin> test01(@RequestParam("array[]") List<Integer> array){
        logger.info("array:"+array);
        ResultEntity<Admin> resultEntity = new ResultEntity<Admin>(ResultEntity.SUCCESS,null,new Admin(null, "一代宗师", "123456", "2046", "123@qq.com", null));
        String s = null;
        System.out.println(s.length());
        //        int i = 10 / 0;
        return resultEntity;
    }
}
