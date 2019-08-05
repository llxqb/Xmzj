package com.xmzj.mvp.model;

import com.google.gson.Gson;
import com.xmzj.network.networkApi.MainApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class MainModel {
    private final MainApi mMainApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public MainModel(MainApi api, Gson gson, ModelTransform transform) {
        mMainApi = api;
        mGson = gson;
        mTransform = transform;
    }


//    //请求我的-首页接口，更新个人信息
//    public Observable<ResponseData> onRequestHomeUserInfo(TokenRequest request) {
//        return mMainApi.onRequestHomeUserInfo(mGson.toJson(request)).map(mTransform::transformCommon);
//    }


}
