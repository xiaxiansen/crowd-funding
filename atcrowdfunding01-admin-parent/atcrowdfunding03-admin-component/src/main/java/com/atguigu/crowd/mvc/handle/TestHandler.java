package com.atguigu.crowd.mvc.handle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.atguigu.crowd.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestHandler {
	
	
	
	private Logger logger = LoggerFactory.getLogger(TestHandler.class);
	
	@ResponseBody
	@RequestMapping("requestbody/one.html")
	public Admin testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
		
		for (Integer number : array) {
			System.out.println("number="+number);
		}
		Admin admin = new Admin(null, "一代宗师", "123456", "2046", "123@qq.com", null);
		return admin;
	}

}
