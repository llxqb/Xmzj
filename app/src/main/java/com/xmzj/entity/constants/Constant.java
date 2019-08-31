package com.xmzj.entity.constants;


/**
 * Created by li.liu on 2017/12/18.
 */

public class Constant {
    /**
     * 公共弹框选择方式1和2,3,4
     */
    public static final int DIALOG_ONE = 1;
    public static final int DIALOG_TWO = 2;
    public static final int DIALOG_THREE = 3;
    public static final int DIALOG_FOUR = 4;
    public static final int DIALOG_FIVE = 5;

    //键盘延时100
    public static final int DELAYTIME = 100;

    //Google登录回调
    public static final int GOOGLE_LOGIN = 100;
    //facebook登录回调
    public static final int FACEBOOK_LOGIN = 64206;

    //图片类型 1头像2封面3相册4举报5消息
    public static final int PIC_AVATOR = 1;
    public static final int PIC_COVER = 2;
    public static final int PIC_ALBUM = 3;
    public static final int PIC_REPORT = 4;
    public static final int PIC_MESSAGE = 5;

    //占位图图片资源

    //发起google支付
    public static final int GOOGLE_PAY_REQ = 10001;

    //recyclerView item 中某一/某些控件刷新
    public static final String ITEM_UPDATE = "itemUpdate";
    //设备标识
    /**
     * 客户端类型(1：安卓，2：苹果，3：电脑浏览器，4：手机浏览器，5：微信)
     */
    public static final int FROM = 1;
    /**
     * 验证码类型(注册：100001，重置密码：100002,登录：100003)
     */
    public static final int VerifyCode_REGISTER = 100001;
    public static final int VerifyCode_RETPWD = 100002;
    public static final int VerifyCode_LOGIN = 100003;

}
