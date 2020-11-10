package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.service.impl
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-30 16:43
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        
        PageHelper.startPage(pageNum,pageSize);
        
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        
        return new PageInfo<Role>(roleList);
    }
    
    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }
    
    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }
    
    @Override
    public void removeRoleByArrayId(List<Integer> arrayId) {
       
        RoleExample example = new RoleExample();
    
        RoleExample.Criteria criteria = example.createCriteria();
        
        criteria.andIdIn(arrayId);
        
        roleMapper.deleteByExample(example);
    }
    
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        
        return roleMapper.selectAssignedRole(adminId);
    }
    
    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }
}
