package com.xmzj.mvp.ui.activity.book;


import com.xmzj.entity.response.BookChapterContentResponse;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class BookDetailControl {
    public interface BookDetailView extends LoadDataView {
        void getChapterListSuccess(ChapterListResponse chapterListResponse);

        void getChapterContentSuccess(BookChapterContentResponse bookChapterContentResponse);
    }

    public interface PresenterBookDetail extends Presenter<BookDetailView> {

        /**
         * 书籍章节列表
         */
        void onRequestChapterList(String bookId);

        /**
         * 书籍章节内容
         */
        void onRequestChapterContent(String bookId,String sectionId);
    }

}
