package com.xmzj.entity.request;

/**
 * 获取验证码
 */
public class VerifyCodeRequest {
    /**
     * 手机号
     */
    public String phoneNum;
    /**
     * 邮箱
     */
    public String email;
    /**
     * 验证类型(注册：100001，重置密码：100002,登录：100003)
     */
    public int type;
}
