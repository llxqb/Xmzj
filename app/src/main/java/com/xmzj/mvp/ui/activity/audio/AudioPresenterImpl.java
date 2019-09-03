package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.AudioClassifyResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.ResponseData;
import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class AudioPresenterImpl implements AudioControl.PresenterAudio {

    private AudioControl.AudioView mAudioView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public AudioPresenterImpl(Context context, VideoModel model, AudioControl.AudioView AudioView) {
        mContext = context;
        mVideoModel = model;
        mAudioView = AudioView;
    }


    /**
     * 音频分类
     */
    @Override
    public void onRequestAudioClassify() {
        mAudioView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestAudioClassify().compose(mAudioView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestAudioClassifySuccess, throwable -> mAudioView.showErrMessage(throwable),
                        () -> mAudioView.dismissLoading());
        mAudioView.addSubscription(disposable);
    }

    private void requestAudioClassifySuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            AudioClassifyResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), AudioClassifyResponse.class);
            mAudioView.getAudioClassifySuccess(response);
        } else {
            mAudioView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
