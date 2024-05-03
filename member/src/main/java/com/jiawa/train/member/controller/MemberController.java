package com.jiawa.train.member.controller;

import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.member.req.MemberLoginReq;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.req.MemberSendCodeReq;
import com.jiawa.train.member.resp.LoginCodeResp;
import com.jiawa.train.member.resp.MemberLoginResp;
import com.jiawa.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     *查询用户记录数量
     */
    @GetMapping("/count")
    public CommonResp<Integer> count(){
        int count = memberService.count();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    /**
     * 注册会员账号：插入用户记录
     * Valid：参数校验，犹如开关，加上后校验打开
     */
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterReq req){
        long register = memberService.register(req);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }

    /**
     *登录功能
     */
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req){
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<MemberLoginResp>(resp);
    }

    /**
     *生成短信验证码
     * RequestBody：接收json类型的参数
     */
    @PostMapping("/sendcode")
    public CommonResp<LoginCodeResp> sendcode(@Valid @RequestBody MemberSendCodeReq req){
        LoginCodeResp resp = memberService.sendCode(req);
//        CommonResp<String> commonResp = new CommonResp<>();
//        commonResp.setContent(loginCodeResp);
//        return commonResp;
        return new CommonResp<LoginCodeResp>(resp);
    }
}
