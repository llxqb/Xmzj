package com.xmzj.mvp.ui.fragment.book;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.request.BooksRequest;
import com.xmzj.entity.response.BooksListResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.BookModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class BooksChildFragmentPresenterImpl implements BooksChildFragmentControl.BooksChildFragmentPresenter {

    private BooksChildFragmentControl.BooksChildFragmentView mBooksChildFragmentView;
    private final BookModel mBookModel;
    private final Context mContext;

    @Inject
    public BooksChildFragmentPresenterImpl(Context context, BookModel model, BooksChildFragmentControl.BooksChildFragmentView view) {
        mContext = context;
        mBookModel = model;
        mBooksChildFragmentView = view;
    }


    /**
     * 请求视频列表
     */
    @Override
    public void onRequestBookList(BooksRequest booksRequest) {
        mBooksChildFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestBookList(booksRequest).compose(mBooksChildFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestAudioListSuccess, throwable -> mBooksChildFragmentView.showErrMessage(throwable),
                        () -> mBooksChildFragmentView.dismissLoading());
        mBooksChildFragmentView.addSubscription(disposable);
    }


    private void requestAudioListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            BooksListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BooksListResponse.class);
            mBooksChildFragmentView.getBooksListSuccess(response);
        } else {
            mBooksChildFragmentView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mBooksChildFragmentView = null;
    }


}
