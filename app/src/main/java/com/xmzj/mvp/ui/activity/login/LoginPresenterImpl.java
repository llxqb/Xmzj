package com.xmzj.mvp.ui.activity.login;

import android.content.Context;

import com.xmzj.R;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.entity.request.VerifyCodeRequest;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.LoginModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class LoginPresenterImpl implements LoginControl.PresenterLogin {

    private LoginControl.LoginView mLoginView;
    private final LoginModel mLoginModel;
    private final Context mContext;

    @Inject
    public LoginPresenterImpl(Context context, LoginModel model, LoginControl.LoginView LoginView) {
        mContext = context;
        mLoginModel = model;
        mLoginView = LoginView;
    }

    /**
     * 登录
     */
    @Override
    public void onRequestLogin(RegisterRequest loginRequest) {
        mLoginView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestLoginInfo(loginRequest).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestLoginSuccess, throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }


    private void requestLoginSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
//            mLoginView.showToast("登录成功");
            mLoginView.getLoginSuccess(responseData.result);
//            responseData.parseData(LoginResponse.class);
//            if (responseData.parsedData != null) {
//                LoginResponse response = (LoginResponse) responseData.parsedData;
//                mLoginView.getLoginSuccess(response);
//            }
        } else {
            mLoginView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 获取验证码
     */
    @Override
    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
        mLoginView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestVerifyCode(verifyCodeRequest).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVerifyCodeSuccess, throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }


    private void requestVerifyCodeSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mLoginView.getVerifyCodeSuccess(responseData.verifyCode);
//            responseData.parseData(ForgetPwdResponse.class);
//            if (responseData.parsedData != null) {
//                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
//                mLoginView.getForgetPwdSuccess(response);
//            }
        } else {
            mLoginView.showToast(responseData.errorMsg);
        }
    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
