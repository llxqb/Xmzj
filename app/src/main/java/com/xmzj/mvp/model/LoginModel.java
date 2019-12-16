package com.xmzj.mvp.model;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.entity.request.VerifyCodeRequest;
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
    public Observable<ResponseData> onRequestLoginInfo(RegisterRequest request) {
        if (!TextUtils.isEmpty(request.phoneNum) || !TextUtils.isEmpty(request.email)) {
            //验证码登录
            return mLoginApi.onRequestLoginByCode(request.phoneNum, request.email, request.code, request.clientType).map(mTransform::transformCommon);
        } else {
            //账号密码登录
            return mLoginApi.onRequestLoginInfo(request.account, request.pwd, request.clientType).map(mTransform::transformCommon);
        }
    }

    /**
     * 注册
     */
    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
        return mLoginApi.onRequestRegister(request.account, request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
    }

    /**
     * 忘记密码
     */
    public Observable<ResponseData> onRequestForgetPwd(RegisterRequest request) {
        return mLoginApi.onRequestForgetPwd(request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
    }

    /**
     * 获取验证码
     */
    public Observable<ResponseData> onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
        return mLoginApi.onRequestVerifyCode(verifyCodeRequest.phoneNum, verifyCodeRequest.email, verifyCodeRequest.type).map(mTransform::transformCommon);
    }
}
