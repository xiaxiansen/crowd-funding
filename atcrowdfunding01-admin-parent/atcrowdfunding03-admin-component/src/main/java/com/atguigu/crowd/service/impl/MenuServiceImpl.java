package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.service.impl
 * @description: TODO
 * @author: xia liang
 * @create: 2020-07-02 15:21
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
    
    @Override
    public void saveMenu(Menu menu) {
        
        menuMapper.insert(menu);
    }
    
    @Override
    public void updateMenu(Menu menu) {
        
        menuMapper.updateByPrimaryKeySelective(menu);
    }
    
    @Override
    public void removeMenu(Integer id) {
        
        menuMapper.deleteByPrimaryKey(id);
    }
}
