package com.xmzj.mvp.ui.activity.video;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.ResponseData;
import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class VideoFragmentPresenterImpl implements VideoFragmentControl.VideoFragmentPresenter {

    private VideoFragmentControl.VideoFragmentView mVideoFragmentView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public VideoFragmentPresenterImpl(Context context, VideoModel model, VideoFragmentControl.VideoFragmentView videoFragmentView) {
        mContext = context;
        mVideoModel = model;
        mVideoFragmentView = videoFragmentView;
    }

    /**
     * 请求视频列表
     */
    @Override
    public void onRequestVideoList(VideoListRequest videoRequest) {
        mVideoFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoList(videoRequest).compose(mVideoFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoListSuccess, throwable -> mVideoFragmentView.showErrMessage(throwable),
                        () -> mVideoFragmentView.dismissLoading());
        mVideoFragmentView.addSubscription(disposable);
    }


    private void requestVideoListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            VideoListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), VideoListResponse.class);
            mVideoFragmentView.getVideoListSuccess(response);
        } else {
            mVideoFragmentView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 视频详情
     */
    @Override
    public void onRequestVideoInfo(String videoId) {
        mVideoFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoInfo(videoId).compose(mVideoFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoInfoSuccess, throwable -> mVideoFragmentView.showErrMessage(throwable),
                        () -> mVideoFragmentView.dismissLoading());
        mVideoFragmentView.addSubscription(disposable);
    }

    private void requestVideoInfoSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(VideoInfoResponse.class);
            if (responseData.parsedData != null) {
                VideoInfoResponse response = (VideoInfoResponse) responseData.parsedData;
                mVideoFragmentView.getVideoInfoSuccess(response);
            }
        } else {
            mVideoFragmentView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mVideoFragmentView = null;
    }
}
