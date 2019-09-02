package com.xmzj.mvp.ui.activity.search;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.ResponseData;
import com.xmzj.mvp.model.VideoModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class SearchPresenterImpl implements SearchControl.PresenterSearch {

    private SearchControl.SearchView mSearchView;
    private final VideoModel mVideoModel;
    private final Context mContext;

    @Inject
    public SearchPresenterImpl(Context context, VideoModel model, SearchControl.SearchView SearchView) {
        mContext = context;
        mVideoModel = model;
        mSearchView = SearchView;
    }

    /**
     * 请求视频列表
     */
    @Override
    public void onRequestVideoList(VideoListRequest videoRequest) {
        mSearchView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mVideoModel.onRequestVideoList(videoRequest).compose(mSearchView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVideoListSuccess, throwable -> mSearchView.showErrMessage(throwable),
                        () -> mSearchView.dismissLoading());
        mSearchView.addSubscription(disposable);
    }


    private void requestVideoListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            VideoListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), VideoListResponse.class);
            mSearchView.getVideoListSuccess(response);
        } else {
            mSearchView.showToast(responseData.errorMsg);
        }
    }
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

}
