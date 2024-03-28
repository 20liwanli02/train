package com.jiawa.train.member.req;

import jakarta.validation.constraints.NotBlank;

public class MemberRegisterReq {

    @NotBlank(message = "手机号不能为空！")//要求mobile不能为空
    private String mobile;

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
