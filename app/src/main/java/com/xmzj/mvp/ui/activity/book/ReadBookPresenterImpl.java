package com.xmzj.mvp.ui.activity.book;

import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.entity.response.BookChapterContentResponse;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.BookModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterBooksImpl
 */

public class ReadBookPresenterImpl implements ReadBookControl.PresenterReadBook {

    private ReadBookControl.ReadBookView mReadBookView;
    private final BookModel mBookModel;
    private final Context mContext;

    @Inject
    public ReadBookPresenterImpl(Context context, BookModel model, ReadBookControl.ReadBookView view) {
        mContext = context;
        mBookModel = model;
        mReadBookView = view;
    }

    /**
     * 书籍章节内容
     */
    @Override
    public void onRequestChapterContent(String bookId, String sectionId) {
        mReadBookView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestChapterContent(bookId, sectionId).compose(mReadBookView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestChapterContentSuccess, throwable -> mReadBookView.showErrMessage(throwable),
                        () -> mReadBookView.dismissLoading());
        mReadBookView.addSubscription(disposable);
    }


    /**
     * 书籍章节内容 成功
     */
    private void requestChapterContentSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            BookChapterContentResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BookChapterContentResponse.class);
            mReadBookView.getChapterContentSuccess(response);
        } else {
            mReadBookView.showToast(responseData.errorMsg);
        }
    }


    /**
     * 书籍章节列表
     */
    @Override
    public void onRequestChapterList(String bookId) {
        mReadBookView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestChapterList(bookId).compose(mReadBookView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestChapterListSuccess, throwable -> mReadBookView.showErrMessage(throwable),
                        () -> mReadBookView.dismissLoading());
        mReadBookView.addSubscription(disposable);
    }


    /**
     * 章节列表 成功
     */
    private void requestChapterListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            ChapterListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), ChapterListResponse.class);
            mReadBookView.getChapterListSuccess(response);
        } else {
            mReadBookView.showToast(responseData.errorMsg);
        }
    }

    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
