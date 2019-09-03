package com.xmzj.mvp.ui.activity.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.di.components.DaggerSearchComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.SearchModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.mvp.ui.activity.video.VideoDetailEpisodeActivity;
import com.xmzj.mvp.ui.adapter.VideoAdapter;
import com.xmzj.mvp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchControl.SearchView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.search_content_et)
    EditText mSearchContentEt;
    @BindView(R.id.search_content_rl)
    RelativeLayout mSearchContentRl;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int page = 1;
    private List<VideoListResponse.DataBean> videoResponseList = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private View mEmptyView;
    @Inject
    SearchControl.PresenterSearch mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_search);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.nothing_layout, (ViewGroup) mRecyclerView.getParent(), false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mVideoAdapter = new VideoAdapter(this, videoResponseList, mImageLoaderHelper);
        mVideoAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mVideoAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VideoListResponse.DataBean dataBean = (VideoListResponse.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.item_video_layout:
                        assert dataBean != null;
                        VideoDetailEpisodeActivity.start(SearchActivity.this, dataBean.getId(), dataBean.getTitle());
                        break;
                    case R.id.upload_iv:
                        showToast("下载...");
                        break;
                    case R.id.share_iv:
                        showToast("分享...");
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.common_back, R.id.search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.search_btn:
                String searchContent = mSearchContentEt.getText().toString();
                if (TextUtils.isEmpty(searchContent)) {
                    showToast("搜索内容不能为空");
                    return;
                }
                page = 1;
                onRequestVideoList(searchContent);
                break;
        }
    }


    /**
     * 搜索视频列表
     */
    private void onRequestVideoList(String keyword) {
        VideoListRequest videoListRequest = new VideoListRequest();
        videoListRequest.categoryId = "";
        videoListRequest.orderCol = "";
        videoListRequest.keyword = keyword;
        videoListRequest.pageNo = page;
        videoListRequest.pageSize =  Constant.PAGESIZE;
        mPresenter.onRequestVideoList(videoListRequest);
    }

    @Override
    public void getVideoListSuccess(VideoListResponse response) {
        LogUtils.d("response:" + new Gson().toJson(response));
        videoResponseList = response.getData();
        mVideoAdapter.addData(videoResponseList);
        if (page == 1) {
            if (!videoResponseList.isEmpty()) {
                mVideoAdapter.setNewData(videoResponseList);
                mVideoAdapter.loadMoreComplete();
            } else {
                //加载Empty布局
                mVideoAdapter.setNewData(videoResponseList);
                mVideoAdapter.setEmptyView(mEmptyView);
            }
        } else {
            mVideoAdapter.addData(videoResponseList);
            mVideoAdapter.loadMoreComplete();
        }
    }

    @Override
    public void getAudioListSuccess(AudioListResponse response) {
    }

    @Override
    public void onLoadMoreRequested() {
        if (page == 1 && videoResponseList.size() < 10) {
            mVideoAdapter.loadMoreEnd(true);
        } else {
            if (videoResponseList.size() < Constant.PAGESIZE) {
                mVideoAdapter.loadMoreEnd();
            } else {
                page++;
                onRequestVideoList(mSearchContentEt.getText().toString());
            }
        }
    }

    private void initInjectData() {
        DaggerSearchComponent.builder().appComponent(getAppComponent())
                .searchModule(new SearchModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
