package com.atguigu.crowd.mvc.handle;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-30 16:49
 */

@Controller
public class RoleHandler {
    
    @Autowired
    private RoleService roleService;
    
    @ResponseBody
    @RequestMapping("/role/remove.json")
    public ResultEntity<String> removeRoleByArrayId(@RequestBody List<Integer> arrayId){
        
        roleService.removeRoleByArrayId(arrayId);
        
        return ResultEntity.successWithoutData();
    }
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(Role role){
        
        roleService.updateRole(role);
        
        return  ResultEntity.successWithoutData();
    }
    
    @ResponseBody
    @RequestMapping("/role/to/save.json")
    public ResultEntity<String> save(Role role){
        
        roleService.saveRole(role);
        
        return ResultEntity.successWithoutData();
    }
    /** springsecurity为资源添加权限，具有部长角色的用户才能访问 */
    @PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo> getPageInfo(
            @RequestParam(value = "pageNum",defaultValue = "1")  Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword",defaultValue = "") String keyword){
    
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        
        return ResultEntity.successWithData(pageInfo);
    }
}









