package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.po.MemberPO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.service.api
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-30 23:00
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);
    
    void saveMember(@RequestBody MemberPO memberPO);
}
