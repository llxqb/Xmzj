package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.ResponseData;
import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class AudioFragmentPresenterImpl implements AudioFragmentControl.AudioFragmentPresenter {

    private AudioFragmentControl.AudioFragmentView mAudioFragmentView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public AudioFragmentPresenterImpl(Context context, VideoModel model, AudioFragmentControl.AudioFragmentView audioFragmentView) {
        mContext = context;
        mVideoModel = model;
        mAudioFragmentView = audioFragmentView;
    }


    /**
     * 请求视频列表
     */
    @Override
    public void onRequestAudioList(VideoListRequest videoRequest) {
        mAudioFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestAudioList(videoRequest).compose(mAudioFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestAudioListSuccess, throwable -> mAudioFragmentView.showErrMessage(throwable),
                        () -> mAudioFragmentView.dismissLoading());
        mAudioFragmentView.addSubscription(disposable);
    }


    private void requestAudioListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            AudioListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), AudioListResponse.class);
            mAudioFragmentView.getAudioListSuccess(response);
        } else {
            mAudioFragmentView.showToast(responseData.errorMsg);
        }
    }

    
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mAudioFragmentView = null;
    }


}
