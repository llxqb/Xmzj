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
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MineFragmentPresenterImpl implements MineFragmentControl.mineFragmentPresenter {

    private MineFragmentControl.MineView mMineView;
    private final MainModel mMineFragmentModel;
    private final Context mContext;

    @Inject
    public MineFragmentPresenterImpl(Context context, MainModel model, MineFragmentControl.MineView homeView) {
        mContext = context;
        mMineFragmentModel = model;
        mMineView = homeView;
    }

    /**
     * 请求版本更新
     */
    @Override
    public void onRequestVersionUpdate() {
        mMineView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineFragmentModel.onRequestVersionUpdate().compose(mMineView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVersionUpdateSuccess, throwable -> mMineView.showErrMessage(throwable),
                        () -> mMineView.dismissLoading());
        mMineView.addSubscription(disposable);
    }

    private void requestVersionUpdateSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            VersionUpdateResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), VersionUpdateResponse.class);
            mMineView.getVersionUpdateSuccess(response);
        } else {
            mMineView.showToast(responseData.errorMsg);
        }
    }
    

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMineView = null;
    }


}
