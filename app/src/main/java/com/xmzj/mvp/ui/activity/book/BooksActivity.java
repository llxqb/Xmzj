package com.xmzj.mvp.ui.activity.book;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xmzj.R;
import com.xmzj.di.components.DaggerBooksComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.BooksModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.BookTypeResponse;
import com.xmzj.mvp.ui.adapter.PageAdapter;
import com.xmzj.mvp.ui.fragment.book.BooksFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 书籍列表
 */
public class BooksActivity extends BaseActivity implements BooksControl.BooksView {
    @Inject
    BooksControl.PresenterBooks mPresenter;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_books);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        onRequestBookType();
    }

    /**
     * 请求书库分类
     */
    private void onRequestBookType() {
        mPresenter.onRequestBookType();
    }


    @OnClick({R.id.back_iv, R.id.search_content_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.search_content_rl:
                showToast("搜索");
                break;
        }
    }

    List<String> titleString = new ArrayList<>();
    @Override
    public void getBookTypeSuccess(BookTypeResponse bookTypeResponse) {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < bookTypeResponse.getData().size(); i++) {
            BookTypeResponse.DataBean dataBean = bookTypeResponse.getData().get(i);
            titleString.add(dataBean.getName());
            fragmentList.add(BooksFragment.getInstance(dataBean));
        }
        if (!fragmentList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(fragmentList.size()-1);
            mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), fragmentList, titleString));
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    private void initInjectData() {
        DaggerBooksComponent.builder().appComponent(getAppComponent())
                .booksModule(new BooksModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
