package com.xmzj.mvp.model;


import com.google.gson.Gson;
import com.xmzj.entity.request.ForgetPwdRequest;
import com.xmzj.entity.request.LoginRequest;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.network.networkApi.LoginApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class LoginModel {
    private final LoginApi mLoginApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public LoginModel(LoginApi api, Gson gson, ModelTransform transform) {
        mLoginApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 登录
     */
    public Observable<ResponseData> onRequestLoginInfo(LoginRequest request) {
        return mLoginApi.onRequestLoginInfo(request.account, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
    }

    /**
     * 注册
     */
    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
        return mLoginApi.onRequestRegister(request.account, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
    }

    /**
     * 忘记密码
     */
    public Observable<ResponseData> onRequestForgetPwd(ForgetPwdRequest request) {
        return mLoginApi.onRequestForgetPwd(mGson.toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 获取验证码
     */
    public Observable<ResponseData> onRequestVerifyCode(String account, int type) {
        return mLoginApi.onRequestVerifyCode(account, type).map(mTransform::transformCommon);
    }
}
