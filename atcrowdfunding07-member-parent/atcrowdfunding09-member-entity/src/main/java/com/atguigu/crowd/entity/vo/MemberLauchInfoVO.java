package com.atguigu.crowd.entity.vo;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.entity.vo
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-02 18:23
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  MemberLauchInfoVO implements Serializable {
        private static final long serialVersionUID=1L;
        // 简单介绍
        private String descriptionSimple;
        // 详细介绍
        private String descriptionDetail;
        // 联系电话
        private String phoneNum;
        // 客服电话
        private String serviceNum;
}
