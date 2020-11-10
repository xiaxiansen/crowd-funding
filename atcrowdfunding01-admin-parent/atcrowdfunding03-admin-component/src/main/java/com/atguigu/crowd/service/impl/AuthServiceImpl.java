package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.service.impl
 * @description: TODO
 * @author: xia liang
 * @create: 2020-07-03 19:18
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthMapper authMapper;
    
    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }
    
    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }
    
    @Override
    public void saveRoleAuthRelationShip(Map<String, List<Integer>> map) {
        
        List<Integer> roleIdList = map.get("roleId");
        
        Integer roleId = roleIdList.get(0);
    
        /** 删除旧的关联关系*/
        authMapper.deleteOldRelationShip(roleId);
    
        List<Integer> authIdList = map.get("authIdArray");
        
        if(authIdList != null || authIdList.size() > 0){
            
            authMapper.insertNewRelationShip(roleId,authIdList);
        }
    }
    
    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
