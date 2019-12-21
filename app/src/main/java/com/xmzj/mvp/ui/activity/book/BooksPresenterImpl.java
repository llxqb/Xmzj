package com.xmzj.mvp.ui.activity.book;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.BookTypeResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.BookModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterBooksImpl
 */

public class BooksPresenterImpl implements BooksControl.PresenterBooks {

    private BooksControl.BooksView mBooksView;
    private final BookModel mBookModel;
    private final Context mContext;

    @Inject
    public BooksPresenterImpl(Context context, BookModel model, BooksControl.BooksView view) {
        mContext = context;
        mBookModel = model;
        mBooksView = view;
    }

    /**
     * 请求书库分类
     */
    @Override
    public void onRequestBookType() {
        mBooksView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestBookType().compose(mBooksView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestBookTypeSuccess, throwable -> mBooksView.showErrMessage(throwable),
                        () -> mBooksView.dismissLoading());
        mBooksView.addSubscription(disposable);
    }


    /**
     * 请求书库分类 成功
     */
    private void requestBookTypeSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            BookTypeResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BookTypeResponse.class);
            mBooksView.getBookTypeSuccess(response);
        } else {
            mBooksView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
