package com.atguigu.crowd.entity.vo;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.entity.vo
 * @description: TODO
 * @author: xia liang
 * @create: 2020-09-02 18:29
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberConfirmInfoVO implements Serializable {
        private static final long serialVersionUID=1L;
        // 易付宝账号
        private String paynum;
        // 法人身份证号
        private String cardnum;
}
