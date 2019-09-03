package com.xmzj.mvp.ui.activity.video;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.VideoClassifyResponse;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.ResponseData;
import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class VideoPresenterImpl implements VideoControl.PresenterVideo {

    private VideoControl.VideoView mVideoView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public VideoPresenterImpl(Context context, VideoModel model, VideoControl.VideoView VideoView) {
        mContext = context;
        mVideoModel = model;
        mVideoView = VideoView;
    }

    /**
     * 视频分类
     */
    @Override
    public void onRequestVideoClassify() {
        mVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoClassify().compose(mVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoClassifySuccess, throwable -> mVideoView.showErrMessage(throwable),
                        () -> mVideoView.dismissLoading());
        mVideoView.addSubscription(disposable);
    }

    private void requestVideoClassifySuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            VideoClassifyResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), VideoClassifyResponse.class);
            mVideoView.getVideoClassifySuccess(response);
        } else {
            mVideoView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 视频详情
     */
    @Override
    public void onRequestVideoInfo(String videoId) {
        mVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoInfo(videoId).compose(mVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoInfoSuccess, throwable -> mVideoView.showErrMessage(throwable),
                        () -> mVideoView.dismissLoading());
        mVideoView.addSubscription(disposable);
    }

    private void requestVideoInfoSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(VideoInfoResponse.class);
            if (responseData.parsedData != null) {
                VideoInfoResponse response = (VideoInfoResponse) responseData.parsedData;
                mVideoView.getVideoInfoSuccess(response);
            }
        } else {
            mVideoView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 视频详情
     */
    @Override
    public void onRequestVideoCollection(String episodeId) {
//        mVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoCollection(episodeId).compose(mVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoCollectionSuccess, throwable -> mVideoView.showErrMessage(throwable),
                        () -> mVideoView.dismissLoading());
        mVideoView.addSubscription(disposable);
    }

    private void requestVideoCollectionSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(VideoInfoResponse.class);
            if (responseData.parsedData != null) {
                VideoInfoResponse response = (VideoInfoResponse) responseData.parsedData;
                mVideoView.getVideoInfoSuccess(response);
            }
        } else {
            mVideoView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

}
