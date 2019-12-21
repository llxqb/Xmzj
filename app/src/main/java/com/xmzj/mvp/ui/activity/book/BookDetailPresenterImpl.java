package com.xmzj.mvp.ui.activity.book;

import android.content.Context;

import com.xmzj.R;
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
//        if (responseData.resultCode == 0) {
//            BookTypeResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BookTypeResponse.class);
//            mBookDetailView.getBookTypeSuccess(response);
//        } else {
//            mBookDetailView.showToast(responseData.errorMsg);
//        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
