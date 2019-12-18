package com.xmzj.mvp.ui.activity.main;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.BannerResponse;
import com.xmzj.entity.response.HomeRecommendAudioResponse;
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
    public void onRequestRecommendAudio() {
        mHomeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mHomeFragmentModel.onRequestRecommendAudio().compose(mHomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestRecommendAudioSuccess, throwable -> mHomeView.showErrMessage(throwable),
                        () -> mHomeView.dismissLoading());
        mHomeView.addSubscription(disposable);
    }


    private void requestRecommendAudioSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(HomeRecommendAudioResponse.class);
            if (responseData.parsedData != null) {
                HomeRecommendAudioResponse response = (HomeRecommendAudioResponse) responseData.parsedData;
                mHomeView.getRecommendAudioSuccess(response);
            }
        } else {
            mHomeView.showToast(responseData.errorMsg);
        }
    }


    /**
     * 请求banner数据
     */
    @Override
    public void onRequestBanner() {
        mHomeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mHomeFragmentModel.onRequestBanner().compose(mHomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestBannerSuccess, throwable -> mHomeView.showErrMessage(throwable),
                        () -> mHomeView.dismissLoading());
        mHomeView.addSubscription(disposable);
    }


    private void requestBannerSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            BannerResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BannerResponse.class);
            mHomeView.getBannerSuccess(response);
        } else {
            mHomeView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mHomeView = null;
    }


}
