package com.xmzj.entity.user;

import java.io.Serializable;

/**
 * Created by li.liu on 2018/1/29.
 * 保存登陆信息
 * sp保存对象必须序列化
 */

public class LoginUser implements Serializable {
    public String token;
    /**
     * 账号
     */
    public String username;
    /**
     * 手机号/邮箱
     */
    public String phoneOrMail;
    /**
     * 密码
     */
    public String pwd;
    /**
     * 头像
     */
    public String avatar;

}
