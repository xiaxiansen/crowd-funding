package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.entity.vo
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-01 10:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String loginacct;
    
    private String userpswd;
    
    private String username;
    
    private String email;
    
    private String phoneNum;
    
    private String code;
}