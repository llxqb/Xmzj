package com.xmzj.mvp.ui.fragment.book;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerBookFragmentComponent;
import com.xmzj.di.modules.BookFragmentModule;
import com.xmzj.di.modules.BooksModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.BooksRequest;
import com.xmzj.entity.response.BooksListResponse;
import com.xmzj.mvp.ui.activity.book.BookDetailActivity;
import com.xmzj.mvp.ui.adapter.BooksAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * AudioFragmentFragment
 */

public class BooksChildFragment extends BaseFragment implements BooksChildFragmentControl.BooksChildFragmentView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.hot_tv)
    TextView mHotTv;
    @BindView(R.id.newest_tv)
    TextView mNewestTv;
    @BindView(R.id.all_tv)
    TextView mAllTv;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<BooksListResponse.DataBean> dataBeanList = new ArrayList<>();
    private BooksAdapter mBooksAdapter;
    private String mType;//子分类id
    private int page;
    private View mEmptyView;
    private String orderCol = "0";//1：热门，2：最新
    @Inject
    BooksChildFragmentControl.BooksChildFragmentPresenter mPresenter;

    public static BooksChildFragment getInstance(String type) {
        BooksChildFragment fragment = new BooksChildFragment();
        Bundle bd = new Bundle();
        bd.putString("type", type);
        fragment.setArguments(bd);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_book, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeInjector();
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.nothing_layout, (ViewGroup) mRecyclerView.getParent(), false);
        mSwipeLy.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeLy.setOnRefreshListener(this);
        page = 1;
        if (getArguments() != null) {
            mType = getArguments().getString("type");
            onRequestBookList();
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mBooksAdapter = new BooksAdapter(dataBeanList, mImageLoaderHelper);
        mRecyclerView.setAdapter(mBooksAdapter);
        mBooksAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BooksListResponse.DataBean dataBean = (BooksListResponse.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    BookDetailActivity.start(getActivity(), dataBean.getId(),dataBean.getName());
                }
            }
        });
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.hot_tv, R.id.newest_tv, R.id.all_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_tv://热门
                page = 1;
                orderCol = "1";
                mHotTv.setTextColor(getResources().getColor(R.color.color_blue_btn));
                mNewestTv.setTextColor(getResources().getColor(R.color.color333));
                mAllTv.setTextColor(getResources().getColor(R.color.color333));
                onRequestBookList();
                break;
            case R.id.newest_tv://最新
                page = 1;
                orderCol = "2";
                mNewestTv.setTextColor(getResources().getColor(R.color.color_blue_btn));
                mHotTv.setTextColor(getResources().getColor(R.color.color333));
                mAllTv.setTextColor(getResources().getColor(R.color.color333));
                onRequestBookList();
                break;
            case R.id.all_tv://全部
                page = 1;
                orderCol = "0";
                mNewestTv.setTextColor(getResources().getColor(R.color.color333));
                mHotTv.setTextColor(getResources().getColor(R.color.color333));
                mAllTv.setTextColor(getResources().getColor(R.color.color_blue_btn));
                onRequestBookList();
                break;
        }
    }

    private void onRequestBookList() {
        BooksRequest booksRequest = new BooksRequest();
        booksRequest.categoryId = mType;
        booksRequest.orderCol = orderCol;
        booksRequest.pageNo = String.valueOf(page);
        booksRequest.pageSize = String.valueOf(Constant.PAGESIZE);
        mPresenter.onRequestBookList(booksRequest);
    }

    @Override
    public void onRefresh() {
        page = 1;
        onRequestBookList();
    }

    boolean isReqState = false;//加载更多 正在请求状态

    @Override
    public void onLoadMoreRequested() {
        if (!isReqState) {
            if (!dataBeanList.isEmpty()) {
                if (page == 1 && dataBeanList.size() < Constant.PAGESIZE) {
                    mBooksAdapter.loadMoreEnd(true);
                } else {
                    if (dataBeanList.size() < Constant.PAGESIZE) {
                        mBooksAdapter.loadMoreEnd();
                    } else {
                        //等于10条
                        page++;
                        mBooksAdapter.loadMoreComplete();
                        onRequestBookList();
                        isReqState = true;
                    }
                }
            } else {
                mBooksAdapter.loadMoreEnd();
            }
        }
    }

    @Override
    public void getBooksListSuccess(BooksListResponse BooksChildListResponse) {
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
        isReqState = false;
        dataBeanList = BooksChildListResponse.getData();
        //加载更多这样设置
        if (!BooksChildListResponse.getData().isEmpty()) {
            if (page == 1) {
                mBooksAdapter.setNewData(BooksChildListResponse.getData());
            } else {
                mBooksAdapter.addData(BooksChildListResponse.getData());
            }
        } else {
            if (page == 1) {
                mBooksAdapter.setNewData(null);
                mBooksAdapter.setEmptyView(mEmptyView);
            }
        }
    }


    private void initializeInjector() {
        DaggerBookFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .booksModule(new BooksModule((AppCompatActivity) getActivity()))
                .bookFragmentModule(new BookFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
