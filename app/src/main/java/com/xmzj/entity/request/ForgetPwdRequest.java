package com.xmzj.entity.request;

public class ForgetPwdRequest {
    /**
     * 账号(手机号码/邮箱)
     */
    public String account;
    /**
     * 使用密码登录
     */
    public String pwd;
    /**
     * 使用验证码登录
     */
    public String code;
    /**
     * 客户端类型(1：安卓，2：苹果，3：电脑浏览器，4：手机浏览器，5：微信)
     */
    public int clientType;
}
