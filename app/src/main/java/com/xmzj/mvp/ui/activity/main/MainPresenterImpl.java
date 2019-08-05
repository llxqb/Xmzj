package com.xmzj.mvp.ui.activity.main;

import android.content.Context;
import android.net.Uri;

import com.xmzj.mvp.model.MainModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class MainPresenterImpl implements MainControl.PresenterMain {

    private MainControl.MainView mMainView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MainPresenterImpl(Context context, MainModel model, MainControl.MainView mainView) {
        mContext = context;
        mMainModel = model;
        mMainView = mainView;
    }



    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
