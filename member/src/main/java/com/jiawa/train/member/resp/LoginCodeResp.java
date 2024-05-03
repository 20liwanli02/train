package com.jiawa.train.member.resp;

/**
 * @Author xiaoke
 * @Description 验证码返回vo
 * @Date 2023/7/24 13:18
 */
public class LoginCodeResp {
    private String code;
    private String key;

    public LoginCodeResp(String code, String key) {
        this.code = code;
        this.key = key;
    }

    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
