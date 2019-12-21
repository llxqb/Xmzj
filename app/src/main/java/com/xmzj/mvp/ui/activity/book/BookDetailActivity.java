package com.xmzj.mvp.ui.activity.book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bifan.txtreaderlib.main.TxtConfig;
import com.bifan.txtreaderlib.ui.HwTxtPlayActivity;
import com.xmzj.R;
import com.xmzj.di.components.DaggerBookDetailComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.BookDetailModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.BookChapterContentResponse;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.mvp.ui.adapter.BookSelectionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 书籍详情
 */
public class BookDetailActivity extends BaseActivity implements BookDetailControl.BookDetailView {
    @Inject
    BookDetailControl.PresenterBookDetail mPresenter;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private String mBookId;
    private String mBookName;
    private BookSelectionAdapter mBookSelectionAdapter;
    private List<ChapterListResponse.DataBean> dataBeanList = new ArrayList<>();

    public static void start(Context context, String bookId, String bookName) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("bookId", bookId);
        intent.putExtra("bookName", bookName);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_book_detail);
        initInjectData();
        setStatusBar();
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText(mBookName);
        mBookSelectionAdapter = new BookSelectionAdapter(dataBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBookSelectionAdapter);
        mBookSelectionAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ChapterListResponse.DataBean dataBean = (ChapterListResponse.DataBean) adapter.getItem(position);
            if (dataBean != null) {
                onRequestChapterContent(dataBean.getId());
            }
        });
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            mBookId = getIntent().getStringExtra("bookId");
            mBookName = getIntent().getStringExtra("bookName");
            onRequestChapterList();
        }
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 书籍章节列表
     */
    private void onRequestChapterList() {
        mPresenter.onRequestChapterList(mBookId);
    }

    @Override
    public void getChapterListSuccess(ChapterListResponse chapterListResponse) {
        mBookSelectionAdapter.setNewData(chapterListResponse.getData());
    }


    /**
     * 书籍章节内容
     */
    private void onRequestChapterContent(String chapterId) {
        mPresenter.onRequestChapterContent(mBookId, chapterId);
    }

    @Override
    public void getChapterContentSuccess(BookChapterContentResponse bookChapterContentResponse) {
        TxtConfig.saveIsOnVerticalPageMode(BookDetailActivity.this, false);
        HwTxtPlayActivity.loadStr(BookDetailActivity.this, bookChapterContentResponse.getData().getContent());
    }


    private void initInjectData() {
        DaggerBookDetailComponent.builder().appComponent(getAppComponent())
                .bookDetailModule(new BookDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
