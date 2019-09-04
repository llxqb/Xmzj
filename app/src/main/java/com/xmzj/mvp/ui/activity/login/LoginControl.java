package com.xmzj.mvp.ui.activity.login;


import com.xmzj.entity.request.LoginRequest;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class LoginControl {
    public interface LoginView extends LoadDataView {
        void getLoginSuccess(String token);
        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterLogin extends Presenter<LoginView> {

        /**
         * 登录
         */
        void onRequestLogin(LoginRequest loginRequest);
        /**
         * 获取验证码
         * 验证类型(注册：100001，重置密码：100002,登录：100003)
         */
        void onRequestVerifyCode(String account,int type);
    }

}
