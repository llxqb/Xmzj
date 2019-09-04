package com.xmzj.mvp.ui.activity.register;

import android.content.Context;

import com.xmzj.R;
import com.xmzj.entity.request.ForgetPwdRequest;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.LoginModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/08/30.
 * RegisterPresenterImpl
 */

public class RegisterPresenterImpl implements RegisterControl.PresenterRegister {

    private RegisterControl.RegisterView mRegisterView;
    private final LoginModel mLoginModel;
    private final Context mContext;

    @Inject
    public RegisterPresenterImpl(Context context, LoginModel model, RegisterControl.RegisterView RegisterView) {
        mContext = context;
        mLoginModel = model;
        mRegisterView = RegisterView;
    }

    /**
     * 注册
     */
    @Override
    public void onRequestRegister(RegisterRequest registerRequest) {
        mRegisterView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestRegister(registerRequest).compose(mRegisterView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestRegisterSuccess, throwable -> mRegisterView.showErrMessage(throwable),
                        () -> mRegisterView.dismissLoading());
        mRegisterView.addSubscription(disposable);
    }

    /**
     * 注册成功
     */
    private void requestRegisterSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mRegisterView.getRegisterSuccess();
//            responseData.parseData(RegisterResponse.class);
//            if (responseData.parsedData != null) {
//                RegisterResponse response = (RegisterResponse) responseData.parsedData;
//                mRegisterView.getRegisterSuccess(response);
//            }
        } else {
            mRegisterView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 忘记密码
     */
    @Override
    public void onRequestForgetPwd(ForgetPwdRequest forgetPwdRequest) {
        mRegisterView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestForgetPwd(forgetPwdRequest).compose(mRegisterView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestForgetPwdSuccess, throwable -> mRegisterView.showErrMessage(throwable),
                        () -> mRegisterView.dismissLoading());
        mRegisterView.addSubscription(disposable);
    }


    private void requestForgetPwdSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mRegisterView.getForgetPwdSuccess();
//            responseData.parseData(ForgetPwdResponse.class);
//            if (responseData.parsedData != null) {
//                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
//                mRegisterView.getForgetPwdSuccess(response);
//            }
        } else {
            mRegisterView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 获取验证码
     */
    @Override
    public void onRequestVerifyCode(String account, int type) {
        mRegisterView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestVerifyCode(account, type).compose(mRegisterView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVerifyCodeSuccess, throwable -> mRegisterView.showErrMessage(throwable),
                        () -> mRegisterView.dismissLoading());
        mRegisterView.addSubscription(disposable);
    }


    private void requestVerifyCodeSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mRegisterView.getVerifyCodeSuccess(responseData.verifyCode);
//            responseData.parseData(ForgetPwdResponse.class);
//            if (responseData.parsedData != null) {
//                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
//                mRegisterView.getForgetPwdSuccess(response);
//            }
        } else {
            mRegisterView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
