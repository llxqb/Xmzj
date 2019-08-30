package com.xmzj.mvp.ui.activity.register;


import com.xmzj.entity.request.ForgetPwdRequest;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.entity.response.ForgetPwdResponse;
import com.xmzj.entity.response.RegisterResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class RegisterControl {
    public interface RegisterView extends LoadDataView {

        void getRegisterSuccess(RegisterResponse registerResponse);
        void getForgetPwdSuccess(ForgetPwdResponse forgetPwdResponse);
    }

    public interface PresenterRegister extends Presenter<RegisterView> {

        /**
         * 注册
         */
        void onRequestRegister(RegisterRequest registerRequest);
        /**
         * 忘记密码
         */
        void onRequestForgetPwd(ForgetPwdRequest forgetPwdRequest);

    }

}
