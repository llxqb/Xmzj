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
    /**
     * 请求视频详情
     */
    public Observable<ResponseData> onRequestVideoInfoByEpisodeId(String videoId,String episodeId) {
        return mAudioAndVideoApi.onRequestVideoInfoByEpisodeId(videoId,episodeId).map(mTransform::transformCommon);
    }

    /**
     * 请求视频收藏
     */
    public Observable<ResponseData> onRequestVideoCollection(String episodeId) {
        return mAudioAndVideoApi.onRequestVideoCollection(episodeId).map(mTransform::transformCommon);
    }

    /**
     * 请求音频分类
     */
    public Observable<ResponseData> onRequestAudioClassify() {
        return mAudioAndVideoApi.onRequestAudioClassify().map(mTransform::transformListType);
    }

    /**
     * 请求音频列表
     */
    public Observable<ResponseData> onRequestAudioList(VideoListRequest videoRequest) {
        return mAudioAndVideoApi.onRequestAudioList(videoRequest.categoryId, videoRequest.orderCol, videoRequest.keyword, videoRequest.pageNo, videoRequest.pageSize).map(mTransform::transformListType);
    }
    /**
     * 请求音频详情
     */
    public Observable<ResponseData> onRequestAudioDetailInfo(String id) {
        return mAudioAndVideoApi.onRequestAudioDetailInfo(id).map(mTransform::transformCommon);
    }
    /**
     * 请求音频收藏
     */
    public Observable<ResponseData> onRequestAudioConnection(String audioId) {
        return mAudioAndVideoApi.onRequestAudioConnection(audioId).map(mTransform::transformCommon);
    }
}
