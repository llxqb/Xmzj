package com.xmzj.mvp.ui.fragment;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerVideoFragmentComponent;
import com.xmzj.di.modules.VideoFragmentModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.mvp.ui.activity.video.VideoDetailActivity;
import com.xmzj.mvp.ui.activity.video.VideoDetailEpisodeActivity;
import com.xmzj.mvp.ui.activity.video.VideoFragmentControl;
import com.xmzj.mvp.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * VideoFragmentFragment
 */

public class VideoFragmentFragment extends BaseFragment implements VideoFragmentControl.VideoFragmentView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    Unbinder unbinder;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<VideoListResponse.DataBean> videoResponseList = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private String mType;//子分类id
    private int page;
    private View mEmptyView;
    VideoListResponse.DataBean dataBean;
    @Inject
    VideoFragmentControl.VideoFragmentPresenter mPresenter;

    public static VideoFragmentFragment getInstance(String type) {
        VideoFragmentFragment fragment = new VideoFragmentFragment();
        Bundle bd = new Bundle();
        bd.putString("type", type);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_video, container, false);
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
            onRequestVideoList();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mVideoAdapter = new VideoAdapter(getActivity(), videoResponseList, mImageLoaderHelper);
        mVideoAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mVideoAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                dataBean = (VideoListResponse.DataBean) adapter.getItem(position);
                if (view.getId() == R.id.item_video_layout) {
                    assert dataBean != null;
//                    VideoDetailEpisodeActivity.start(getActivity(), dataBean.getId(), dataBean.getTitle());
                    mPresenter.onRequestVideoInfo(dataBean.getId());
                }
            }
        });
    }

    @Override
    public void initData() {
    }


    private void onRequestVideoList() {
        VideoListRequest videoListRequest = new VideoListRequest();
        videoListRequest.categoryId = mType;
        videoListRequest.orderCol = "";
        videoListRequest.keyword = "";
        videoListRequest.pageNo = page;
        videoListRequest.pageSize = Constant.PAGESIZE;
        mPresenter.onRequestVideoList(videoListRequest);
    }

    /**
     * 获取视频列表
     */
    @Override
    public void getVideoListSuccess(VideoListResponse response) {
//        LogUtils.d("response:" + new Gson().toJson(response));
        videoResponseList = response.getData();
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
        if (page == 1) {
            if (videoResponseList.size() > 0) {
                mVideoAdapter.setNewData(videoResponseList);
                mVideoAdapter.loadMoreComplete();
            } else {
//                加载Empty布局
                mVideoAdapter.setEmptyView(mEmptyView);
            }
        } else {
            mVideoAdapter.addData(videoResponseList);
            mVideoAdapter.loadMoreComplete();
        }
    }


    @Override
    public void getVideoInfoSuccess(VideoInfoResponse videoInfoResponse) {
//        LogUtils.e("videoInfoResponse:" + new Gson().toJson(videoInfoResponse));
        VideoInfoResponse.EpisodeBean episodeBean = videoInfoResponse.getEpisode();
        if (!videoInfoResponse.getEpisodes().isEmpty()) {
            VideoDetailEpisodeActivity.start(getActivity(), (ArrayList<VideoInfoResponse.EpisodesBean>) videoInfoResponse.getEpisodes(), dataBean.getTitle());
        } else {
            VideoDetailActivity.start(getActivity(), episodeBean.getVideoId(),null);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        onRequestVideoList();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        if (page == 1 && videoResponseList.size() < Constant.PAGESIZE) {
            mVideoAdapter.loadMoreEnd(true);
        } else {
            if (videoResponseList.size() < Constant.PAGESIZE) {
                mVideoAdapter.loadMoreEnd();
            } else {
                page++;
                onRequestVideoList();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeInjector() {
        DaggerVideoFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .videoModule(new VideoModule((AppCompatActivity) getActivity()))
                .videoFragmentModule(new VideoFragmentModule(this))
                .build().inject(this);
    }


}
