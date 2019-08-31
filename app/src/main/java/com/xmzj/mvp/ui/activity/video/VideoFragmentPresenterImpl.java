package com.xmzj.mvp.ui.activity.video;

import android.content.Context;

import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

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


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mVideoFragmentView = null;
    }


}
