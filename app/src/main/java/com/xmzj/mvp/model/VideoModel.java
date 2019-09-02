package com.xmzj.mvp.model;

import com.google.gson.Gson;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.network.networkApi.AudioAndVideoApi;

import javax.inject.Inject;

import io.reactivex.Observable;

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

    /**
     * 请求视频分类
     */
    public Observable<ResponseData> onRequestVideoClassify() {
        return mAudioAndVideoApi.onRequestVideoClassify().map(mTransform::transformListType);
    }

    /**
     * 请求视频列表
     */
    public Observable<ResponseData> onRequestVideoList(VideoListRequest videoRequest) {
        return mAudioAndVideoApi.onRequestVideoList(videoRequest.categoryId, videoRequest.orderCol, videoRequest.keyword, videoRequest.pageNo, videoRequest.pageSize).map(mTransform::transformListType);
    }
    /**
     * 请求视频详情
     */
    public Observable<ResponseData> onRequestVideoInfo(String videoId) {
        return mAudioAndVideoApi.onRequestVideoInfo(videoId).map(mTransform::transformCommon);
    }


}