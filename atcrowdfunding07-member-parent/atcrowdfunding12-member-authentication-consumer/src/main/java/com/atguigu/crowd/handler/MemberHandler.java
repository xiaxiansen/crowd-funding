package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.config.ShortMessageProperties;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.vo.MemberLoginVO;
import com.atguigu.crowd.entity.vo.MemberVO;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.handler
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-31 23:46
 */
@Controller
public class MemberHandler {
    @Autowired
    private ShortMessageProperties shortMessageProperties;
    @Autowired
    private RedisRemoteService redisRemoteService;
    @Autowired
    private MySQLRemoteService mySQLRemoteService;
    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping("/auth/member/do/login")
    public String login(@RequestParam("loginacct")String loginacct,
                        @RequestParam("userpswd")String userpswd,
                        ModelMap modelMap, HttpSession session){
        //远程调用mysql查询loginacct
        ResultEntity<MemberPO> loginAcctRemoteResultEntity = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        if(ResultEntity.FAILED.equals(loginAcctRemoteResultEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,loginAcctRemoteResultEntity.getMessage());
            return "member-login";
        }
        MemberPO memberPO = loginAcctRemoteResultEntity.getData();
        if(memberPO == null){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        //比较密码
        String userpswdDataBase = memberPO.getUserpswd();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matchesResult = passwordEncoder.matches(userpswd, userpswdDataBase);
        if(!matchesResult){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER, memberLoginVO);
    
        return "redirect:http://www.crowd.com/auth/member/to/center/page";
    }
    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO  memberVO, ModelMap modelMap){
        //获取手机号
        String phoneNum = memberVO.getPhoneNum();
        //拼接redis 验证码的key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
        //判断验证码是否有效
        ResultEntity<String>  redisRemoteResultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);
        String result = redisRemoteResultEntity.getResult();
        if(ResultEntity.FAILED.equals(result)){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,redisRemoteResultEntity.getMessage());
            return "member-reg";
        }
        String redisCode = redisRemoteResultEntity.getData();
        //如果存储redis验证码为空
        if(redisCode == null){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "member-reg";
        }
        String formCode = memberVO.getCode();
        //提交验证码与redis存储的验证码不一致
        if(!Objects.equals(redisCode, formCode)){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,CrowdConstant.MESSAGE_CODE_INVALID);
            return "member-reg";
        }
        //验证码正确就移除redis中的验证码
        redisRemoteService.removeRedisKeyRemote(key);
        //给密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userPasswordBeforeEncode = memberVO.getUserpswd();
        String userPasswordAfterEncode = passwordEncoder.encode(userPasswordBeforeEncode);
        memberVO.setUserpswd(userPasswordAfterEncode);
        MemberPO memberPO = new MemberPO();
        //将表单提交的数据转换为数据库需要存储的数据
        BeanUtils.copyProperties(memberVO, memberPO);
        //调用mysql存储用户注册信息
        ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMmber(memberPO);
        if(ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.ATTR_NANE_MESSAGE,saveMemberResultEntity.getMessage());
            return "member-reg";
        }
        return "redirect:http://www.crowd.com/auth/member/to/login/page";
    }
    
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(shortMessageProperties.getHost(),
                shortMessageProperties.getPath(), shortMessageProperties.getMethod(),
                shortMessageProperties.getPhoneNum(), shortMessageProperties.getAppCode());
        //判断短信发送结果
        if(ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())){
            //如果发送成功,将验证码存入Redis
            String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
            String code = sendMessageResultEntity.getData();
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeOut(key, code, 15L, TimeUnit.MINUTES);
            if(ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())){
                return ResultEntity.successWithoutData();
            }else {
                return saveCodeResultEntity;
            }
        }else {
            return sendMessageResultEntity;
        }
    }
}
