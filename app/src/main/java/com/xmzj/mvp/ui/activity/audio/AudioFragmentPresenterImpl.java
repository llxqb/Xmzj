package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;

import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

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


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mAudioFragmentView = null;
    }


}
