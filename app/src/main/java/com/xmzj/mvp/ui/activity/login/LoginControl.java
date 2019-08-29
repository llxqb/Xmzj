package com.xmzj.mvp.ui.activity.login;


import com.xmzj.entity.request.LoginRequest;
import com.xmzj.entity.response.LoginResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class LoginControl {
    public interface LoginView extends LoadDataView {

        void getLoginSuccess(LoginResponse loginResponse);
    }

    public interface PresenterLogin extends Presenter<LoginView> {

        /**
         * 登录
         */
        void onRequestLogin(LoginRequest loginRequest);

    }

}
