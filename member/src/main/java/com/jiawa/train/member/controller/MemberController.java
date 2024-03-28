package com.jiawa.train.member.controller;

import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @PostMapping("register")
    public CommonResp<Long> register(MemberRegisterReq req){
        long register = memberService.register(req);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }
}
