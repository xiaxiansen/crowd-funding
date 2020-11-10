package com.atguigu.crowd.mvc.handle;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.AuthService;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-07-03 12:09
 */
@Controller
public class AssignHandler {
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AuthService authService;
    
    @ResponseBody
    @RequestMapping("/assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelationShip(
            @RequestBody Map<String,List<Integer>> map){
        
        authService.saveRoleAuthRelationShip(map);
        
        return ResultEntity.successWithoutData();
    }
    
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId")Integer roleId){
        
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        
        return ResultEntity.successWithData(authIdList);
    }
    
    @ResponseBody
    @RequestMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){
        
        List<Auth> authList = authService.getAll();
        
        return ResultEntity.successWithData(authList);
    }
    
    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationShip(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam( "keyword") String keyword,
            @RequestParam(value = "roleIdList",required = false)List<Integer> roleIdList){
    
        adminService.saveAdminRoleRelationShip(adminId,roleIdList);
    
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+ keyword;
    
    }
    
    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam("adminId")Integer adminId,
            Model model){
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        
        model.addAttribute("assignedRoleList",assignedRoleList);
        model.addAttribute("unAssignedRoleList",unAssignedRoleList);
    
        return "assign-role";
    }
}













