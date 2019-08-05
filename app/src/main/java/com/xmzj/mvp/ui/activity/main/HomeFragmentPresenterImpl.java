package com.xmzj.mvp.ui.activity.main;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.MainModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class HomeFragmentPresenterImpl implements HomeFragmentControl.homeFragmentPresenter {

    private HomeFragmentControl.HomeView mHomeView;
    private final MainModel mHomeFragmentModel;
    private final Context mContext;

    @Inject
    public HomeFragmentPresenterImpl(Context context, MainModel model, HomeFragmentControl.HomeView homeView) {
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
