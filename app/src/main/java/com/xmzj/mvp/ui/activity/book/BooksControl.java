package com.xmzj.mvp.ui.activity.book;


import com.xmzj.entity.response.BookTypeResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class BooksControl {
    public interface BooksView extends LoadDataView {
        void getBookTypeSuccess(BookTypeResponse bookTypeResponse);
    }

    public interface PresenterBooks extends Presenter<BooksView> {

        /**
         * 请求书库分类
         */
        void onRequestBookType();

    }

}
