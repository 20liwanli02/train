package com.jiawa.train.member.controller;

import com.jiawa.train.member.service.MemberService;
import jakarta.annotation.Resource;
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
    public Integer count(){
        return memberService.count();
    }

    /**
     * 注册会员账号：插入用户记录
     */
    @PostMapping("register")
    public long register(String mobile){
        return memberService.register(mobile);
    }
}
