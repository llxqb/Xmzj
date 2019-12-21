package com.xmzj.mvp.ui.activity.book;


import com.xmzj.entity.response.BookChapterContentResponse;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class ReadBookControl {
    public interface ReadBookView extends LoadDataView {
        void getChapterContentSuccess(BookChapterContentResponse bookChapterContentResponse);

        void getChapterListSuccess(ChapterListResponse chapterListResponse);
    }

    public interface PresenterReadBook extends Presenter<ReadBookView> {
        /**
         * 书籍章节内容
         */
        void onRequestChapterContent(String bookId, String sectionId);
        /**
         * 书籍章节列表
         */
        void onRequestChapterList(String bookId);
    }

}
