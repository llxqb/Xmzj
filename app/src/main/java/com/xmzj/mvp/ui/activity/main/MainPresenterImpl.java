package com.xmzj.mvp.ui.activity.main;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.VersionUpdateResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.MainModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


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

    /**
     * 请求版本更新
     */
    @Override
    public void onRequestVersionUpdate() {
        mMainView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestVersionUpdate().compose(mMainView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVersionUpdateSuccess, throwable -> mMainView.showErrMessage(throwable),
                        () -> mMainView.dismissLoading());
        mMainView.addSubscription(disposable);
    }

    private void requestVersionUpdateSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            VersionUpdateResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), VersionUpdateResponse.class);
            mMainView.getVersionUpdateSuccess(response);
        } else {
            mMainView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
