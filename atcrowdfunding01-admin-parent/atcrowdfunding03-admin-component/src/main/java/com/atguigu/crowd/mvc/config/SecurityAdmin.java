package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @program: Crowd-funding -- com.atguigu.security.config
 * @description: User对象中仅包含账号和密码，创建这个类对User类进行扩展
 * @author: xia liang
 * @create: 2020-07-04 23:53
 */
public class SecurityAdmin extends User {
    
    private static final long serialVersionUID = 1L;
   
    /** 原始的Admin对象,包含Admin的所有属性 */
    private Admin originalAdmin;
    
    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities){
        
        super(originalAdmin.getLoginAcct(),originalAdmin.getUserPswd(),authorities);
        
        this.originalAdmin = originalAdmin;
        
        //将原始Admin对象的密码擦除,检查密码是由父类 User 执行
        this.originalAdmin.setUserPswd(null);
    }
    
    public Admin getOriginalAdmin() {
        
        return originalAdmin;
    
    }
}
