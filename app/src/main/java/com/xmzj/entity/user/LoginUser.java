package com.xmzj.entity.user;

import java.io.Serializable;

/**
 * Created by li.liu on 2018/1/29.
 * 保存登陆信息
 * sp保存对象必须序列化
 */

public class LoginUser implements Serializable {
    public String token;
    public String nick;
    public String phone;
    public String pwd;
    public String cover;

}
