package com.xmzj.mvp.model;


import com.google.gson.Gson;
import com.xmzj.entity.request.LoginRequest;
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
    public Observable<ResponseData> onRequestHomeUserInfo(LoginRequest request) {
        return mLoginApi.onRequestLoginInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }


}
