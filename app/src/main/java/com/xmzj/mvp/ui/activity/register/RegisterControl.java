package com.xmzj.mvp.ui.activity.register;


import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.entity.request.VerifyCodeRequest;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class RegisterControl {
    public interface RegisterView extends LoadDataView {
        void getRegisterSuccess();
        void getForgetPwdSuccess();
        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterRegister extends Presenter<RegisterView> {

        /**
         * 注册
         */
        void onRequestRegister(RegisterRequest registerRequest);
        /**
         * 忘记密码
         */
        void onRequestForgetPwd(RegisterRequest forgetPwdRequest);
        /**
         * 获取验证码
         * 验证类型(注册：100001，重置密码：100002,登录：100003)
         */
        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);

    }

}
