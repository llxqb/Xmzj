package com.xmzj.mvp.ui.activity.book;

import android.content.Context;
import android.content.Intent;

import com.xmzj.R;
import com.xmzj.di.components.DaggerBookDetailComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.BookDetailModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.ChapterListResponse;

import javax.inject.Inject;

/**
 * 书籍详情
 */
public class BookDetailActivity extends BaseActivity implements BookDetailControl.BookDetailView {
    @Inject
    BookDetailControl.PresenterBookDetail mPresenter;
    private String mBookId;

    public static void start(Context context, String bookId) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("bookId", bookId);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_book_detail);
        initInjectData();
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mBookId = getIntent().getStringExtra("bookId");
            onRequestChapterList();
        }
    }

    @Override
    protected void initData() {

    }

    /**
     * 书籍章节列表
     */
    private void onRequestChapterList() {
        mPresenter.onRequestChapterList(mBookId);
    }

    @Override
    public void getChapterListSuccess(ChapterListResponse chapterListResponse) {

    }


    private void initInjectData() {
        DaggerBookDetailComponent.builder().appComponent(getAppComponent())
                .bookDetailModule(new BookDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
