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

public class BookDetailPresenterImpl implements BookDetailControl.PresenterBookDetail {

    private BookDetailControl.BookDetailView mBookDetailView;
    private final BookModel mBookModel;
    private final Context mContext;

    @Inject
    public BookDetailPresenterImpl(Context context, BookModel model, BookDetailControl.BookDetailView view) {
        mContext = context;
        mBookModel = model;
        mBookDetailView = view;
    }

    /**
     * 书籍章节列表
     */
    @Override
    public void onRequestChapterList(String bookId) {
        mBookDetailView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestChapterList(bookId).compose(mBookDetailView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestChapterListSuccess, throwable -> mBookDetailView.showErrMessage(throwable),
                        () -> mBookDetailView.dismissLoading());
        mBookDetailView.addSubscription(disposable);
    }


    /**
     * 章节列表 成功
     */
    private void requestChapterListSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            ChapterListResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), ChapterListResponse.class);
            mBookDetailView.getChapterListSuccess(response);
        } else {
            mBookDetailView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 书籍章节内容
     */
    @Override
    public void onRequestChapterContent(String bookId, String sectionId) {
        mBookDetailView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBookModel.onRequestChapterContent(bookId, sectionId).compose(mBookDetailView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestChapterContentSuccess, throwable -> mBookDetailView.showErrMessage(throwable),
                        () -> mBookDetailView.dismissLoading());
        mBookDetailView.addSubscription(disposable);
    }


    /**
     * 书籍章节内容 成功
     */
    private void requestChapterContentSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            BookChapterContentResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BookChapterContentResponse.class);
            mBookDetailView.getChapterContentSuccess(response);
        } else {
            mBookDetailView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
