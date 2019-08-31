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
import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerVideoFragmentComponent;
import com.xmzj.di.modules.VideoFragmentModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.entity.response.VideoResponse;
import com.xmzj.mvp.ui.activity.video.VideoDetailActivity;
import com.xmzj.mvp.ui.activity.video.VideoFragmentControl;
import com.xmzj.mvp.ui.adapter.VideoAdapter;
import com.xmzj.mvp.utils.LogUtils;

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
    //    @BindView(R.id.video_banner)
//    Banner mVideoBanner;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<VideoListResponse.DataBean> videoResponseList = new ArrayList<>();
    /**
     * banner集合
     */
    private List<String> bannerImgList = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private String mType;//子分类id
    private int page;
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
                VideoResponse videoResponse = (VideoResponse) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.item_video_layout:
                        assert videoResponse != null;
                        VideoDetailActivity.start(getActivity(), videoResponse);
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
        //初始化banner
//        initBanner();
    }

    @Override
    public void initData() {
    }


    /**
     * banner
     */
//    private void initBanner() {
//        //设置图片加载器
//        mVideoBanner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        String bannerImg1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564997869677&di=08d1d748d6cdbdb2e6077cc643cd61fe&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20141230%2Fshikurulaifozu_3798005.jpg";
//        String bannerImg2 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1540334387,2375027868&fm=26&gp=0.jpg";
//        bannerImgList.add(bannerImg1);
//        bannerImgList.add(bannerImg2);
//        mVideoBanner.setImages(bannerImgList);
//        //设置轮播时间
//        mVideoBanner.setDelayTime(5000);
//        //banner设置方法全部调用完毕时最后调用
//        mVideoBanner.start();
//    }
    private void onRequestVideoList() {
        VideoListRequest videoListRequest = new VideoListRequest();
        videoListRequest.categoryId = mType;
        videoListRequest.pageNo = page;
        videoListRequest.pageSize = 10;
        mPresenter.onRequestVideoList(videoListRequest);
    }

    /**
     * 获取视频列表
     */
    @Override
    public void getVideoListSuccess(VideoListResponse response) {
        LogUtils.d("response:" + new Gson().toJson(response));
        videoResponseList = response.getData();
        mVideoAdapter.addData(videoResponseList);
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
        if (page == 1) {
            if (videoResponseList.size() > 0) {
                mVideoAdapter.setNewData(videoResponseList);
                mVideoAdapter.loadMoreComplete();
            } else {
                //加载Empty布局
//                mVideoAdapter.setEmptyView(mEmptyView);
            }
        } else {
            mVideoAdapter.addData(videoResponseList);
            mVideoAdapter.loadMoreComplete();
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
        if (page == 1 && videoResponseList.size() < 10) {
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
