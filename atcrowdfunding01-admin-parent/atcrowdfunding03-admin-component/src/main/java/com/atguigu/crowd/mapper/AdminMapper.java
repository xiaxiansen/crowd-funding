package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import java.util.List;

import com.atguigu.crowd.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RoleList;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> selectAdminByKeyword(String keyword);
    
    void deletedOldRelationShip(Integer adminId);
    
    void insertNewRelationShip(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}