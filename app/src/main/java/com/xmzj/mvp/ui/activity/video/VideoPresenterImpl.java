package com.xmzj.mvp.ui.activity.video;

import android.content.Context;

import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class VideoPresenterImpl implements VideoControl.PresenterVideo{

    private VideoControl.VideoView mVideoView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public VideoPresenterImpl(Context context, VideoModel model, VideoControl.VideoView VideoView) {
        mContext = context;
        mVideoModel = model;
        mVideoView = VideoView;
    }



    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
