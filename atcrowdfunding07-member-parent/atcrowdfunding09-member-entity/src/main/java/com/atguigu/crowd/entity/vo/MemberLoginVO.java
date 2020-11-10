package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @program: Crowd-funding -- com.atguigu.crowd.entity.vo
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-01 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginVO implements Serializable {
    private static final long serialVersionUID = 2L;
    private Integer id;
    private String username;
    private String email;
}
