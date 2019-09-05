package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.AudioClassifyResponse;
import com.xmzj.entity.response.AudioDetailInfoResponse;
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

    /**
     * 音频详情
     */
    @Override
    public void onRequestAudioDetailInfo(String id) {
        mAudioView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestAudioDetailInfo(id).compose(mAudioView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestAudioDetailInfoSuccess, throwable -> mAudioView.showErrMessage(throwable),
                        () -> mAudioView.dismissLoading());
        mAudioView.addSubscription(disposable);
    }

    private void requestAudioDetailInfoSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(AudioDetailInfoResponse.class);
            if (responseData.parsedData != null) {
                AudioDetailInfoResponse response = (AudioDetailInfoResponse) responseData.parsedData;
                mAudioView.getAudioDetailInfoSuccess(response);
            }
        } else {
            mAudioView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 音频收藏
     */
    @Override
    public void onRequestAudioConnection(String audioId) {
        mAudioView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestAudioConnection(audioId).compose(mAudioView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestAudioConnectionSuccess, throwable -> mAudioView.showErrMessage(throwable),
                        () -> mAudioView.dismissLoading());
        mAudioView.addSubscription(disposable);
    }

    private void requestAudioConnectionSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mAudioView.getAudioConnectionSuccess();
//            responseData.parseData(AudioDetailInfoResponse.class);
//            if (responseData.parsedData != null) {
//                AudioDetailInfoResponse response = (AudioDetailInfoResponse) responseData.parsedData;
//                mAudioView.getAudioDetailInfoSuccess(response);
//            }
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
