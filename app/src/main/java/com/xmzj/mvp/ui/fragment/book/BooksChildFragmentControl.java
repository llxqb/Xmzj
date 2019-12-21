package com.xmzj.mvp.ui.fragment.book;


import com.xmzj.entity.request.BooksRequest;
import com.xmzj.entity.response.BooksListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class BooksChildFragmentControl {
    public interface BooksChildFragmentView extends LoadDataView {
        void getBooksListSuccess(BooksListResponse BooksChildListResponse);
    }

    public interface BooksChildFragmentPresenter extends Presenter<BooksChildFragmentView> {
        /**
         * 请求书籍列表
         */
        void onRequestBookList(BooksRequest videoRequest);
    }


}
