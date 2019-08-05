package com.xmzj.mvp.ui.activity.main;

import android.content.Context;

import com.xmzj.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MoreFragmentPresenterImpl implements MoreFragmentControl.moreFragmentPresenter {

    private MoreFragmentControl.HomeView mHomeView;
    private final MainModel mHomeFragmentModel;
    private final Context mContext;

    @Inject
    public MoreFragmentPresenterImpl(Context context, MainModel model, MoreFragmentControl.HomeView homeView) {
        mContext = context;
        mHomeFragmentModel = model;
        mHomeView = homeView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mHomeView = null;
    }


}
