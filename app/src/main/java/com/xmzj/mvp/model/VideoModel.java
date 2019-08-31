package com.xmzj.mvp.model;

import com.google.gson.Gson;
import com.xmzj.network.networkApi.AudioAndVideoApi;

import javax.inject.Inject;

/**
 * Created by li.liu on 2017/4/28.
 * VideoModel
 */

public class VideoModel {
    private final AudioAndVideoApi mAudioAndVideoApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public VideoModel(AudioAndVideoApi api, Gson gson, ModelTransform transform) {
        mAudioAndVideoApi = api;
        mGson = gson;
        mTransform = transform;
    }


//    //请求我的-首页接口，更新个人信息
//    public Observable<ResponseData> onRequestHomeUserInfo(TokenRequest request) {
//        return mAudioAndVideoApi.onRequestHomeUserInfo(mGson.toJson(request)).map(mTransform::transformCommon);
//    }


}
