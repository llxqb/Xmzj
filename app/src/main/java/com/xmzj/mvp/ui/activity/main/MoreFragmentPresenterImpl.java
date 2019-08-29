package com.xmzj.mvp.ui.activity.main;

import android.content.Context;

import com.xmzj.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MoreFragmentPresenterImpl implements MoreFragmentControl.moreFragmentPresenter {

    private MoreFragmentControl.MoreFragmentView mMoreFragmentView;
    private final MainModel mHomeFragmentModel;
    private final Context mContext;

    @Inject
    public MoreFragmentPresenterImpl(Context context, MainModel model, MoreFragmentControl.MoreFragmentView moreFragmentView) {
        mContext = context;
        mHomeFragmentModel = model;
        mMoreFragmentView = moreFragmentView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMoreFragmentView = null;
    }


}
