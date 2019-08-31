package com.xmzj.mvp.ui.activity.video;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.VideoClassifyResponse;
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

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

}
