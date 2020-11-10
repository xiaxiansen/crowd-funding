package com.atguigu.crowd.mvc.handle;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.service.api.MenuService;
import com.atguigu.crowd.util.ResultEntity;
import com.sun.tools.hat.internal.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.mvc.handle
 * @description: TODO
 * @author: xia liang
 * @create: 2020-07-02 15:23
 */
@RestController
public class MenuHandler {

    @Autowired
    private MenuService menuService;
    
    //@ResponseBody
    @RequestMapping("/menu/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id){
        
        menuService.removeMenu(id);
        
        return ResultEntity.successWithoutData();
    }
    
    //@ResponseBody
    @RequestMapping("/menu/update.json")
    public ResultEntity<String> update(Menu menu){
        
        menuService.updateMenu(menu);
        
        return ResultEntity.successWithoutData();
    }
    
    //@ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> save(Menu menu) throws InterruptedException {
        
        menuService.saveMenu(menu);
    
        return ResultEntity.successWithoutData();
    }
    
    //@ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getAll(){
        
        Menu root = null;
    
        List<Menu> menuList = menuService.getAll();
    
        Map<Integer,Menu> map = new HashMap<Integer, Menu>(30);
        
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            map.put(id,menu);
        }
    
        for (Menu menu : menuList) {
            
            Integer pid = menu.getPid();
            
            if(pid == null){
                root = menu;
                continue;
            }
    
            Menu parent = map.get(pid);
            
            parent.getChildren().add(menu);
        }
        return ResultEntity.successWithData(root);
    }
}





















