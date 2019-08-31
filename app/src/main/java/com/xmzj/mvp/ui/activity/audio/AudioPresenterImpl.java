package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;

import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class AudioPresenterImpl implements AudioControl.PresenterAudio{

    private AudioControl.AudioView mAudioView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public AudioPresenterImpl(Context context, VideoModel model, AudioControl.AudioView AudioView) {
        mContext = context;
        mVideoModel = model;
        mAudioView = AudioView;
    }



    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
