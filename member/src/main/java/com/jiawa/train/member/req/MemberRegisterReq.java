package com.jiawa.train.member.req;

import jakarta.validation.constraints.NotBlank;

public class MemberRegisterReq {

    @NotBlank(message = "手机号不能为空！")//要求mobile不能为空
    private String mobile;

    @NotBlank(message = "验证码不能为空！")//要求code不能为空
    private String code;

    @NotBlank(message = "请先获取验证码！")
    private String keyRedis;

    public String getKeyRedis() {
        return keyRedis;
    }

    public void setKeyRedis(String keyRedis) {
        this.keyRedis = keyRedis;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberRegisterReq{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", keyRedis='").append(keyRedis).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
