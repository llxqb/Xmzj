package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerAudioFragmentComponent;
import com.xmzj.di.modules.AudioFragmentModule;
import com.xmzj.di.modules.AudioModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.ui.activity.audio.AudioFragmentControl;
import com.xmzj.mvp.ui.activity.audio.AudioPlayDetailActivity;
import com.xmzj.mvp.ui.adapter.AudioAdapter;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.KbWithWordsCircleProgressBar;

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

public class AudioFragmentFragment extends BaseFragment implements AudioFragmentControl.AudioFragmentView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.upload_all_tv)
    TextView mUploadAllTv;
    @BindView(R.id.hot_tv)
    TextView mHotTv;
    @BindView(R.id.newest_tv)
    TextView mNewestTv;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<AudioListResponse.DataBean> audioContentResponseList = new ArrayList<>();
    private AudioAdapter mAudioAdapter;
    private String mType;//子分类id
    /**
     * 视频url路径
     */
    String urlPath;
    /**
     * 下载到本地视频路径
     */
    String mVideoPath;
    private int page;
    private View mEmptyView;
    FrameLayout mCircleProgressLayout;
    KbWithWordsCircleProgressBar mCircleProgress;
    @Inject
    AudioFragmentControl.AudioFragmentPresenter mPresenter;

    public static AudioFragmentFragment getInstance(String type) {
        AudioFragmentFragment fragment = new AudioFragmentFragment();
        Bundle bd = new Bundle();
        bd.putString("type", type);
        fragment.setArguments(bd);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_audio, container, false);
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
            onRequestAudioList();
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAudioAdapter = new AudioAdapter(getActivity(), audioContentResponseList);
        mRecyclerView.setAdapter(mAudioAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AudioListResponse.DataBean dataBean = (AudioListResponse.DataBean) adapter.getItem(position);

                mCircleProgressLayout = (FrameLayout) adapter.getViewByPosition(mRecyclerView, position, R.id.fl_circle_progress);
                mCircleProgress = (KbWithWordsCircleProgressBar) adapter.getViewByPosition(mRecyclerView, position, R.id.circle_progress);

                switch (view.getId()) {
                    case R.id.audio_item_layout:
                        assert dataBean != null;
                        AudioPlayDetailActivity.start(getActivity(), dataBean);
                        break;
                    case R.id.upload_iv:
                        assert dataBean != null;
                        urlPath = dataBean.getDownloadUrl();
                        if (!TextUtils.isEmpty(urlPath)) {
                            if (TextUtils.isEmpty(DownloadUtil.checkFileIsExist(urlPath))) {
                                downloadVideo(); //处理具体下载过程
                            } else {
                                showToast("已下载");
                            }
                        } else {
                            showToast("资源不存在");
                        }
                        break;
                    case R.id.share_iv:
                        showToast("分享...");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.upload_all_tv, R.id.hot_tv, R.id.newest_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_all_tv:
                break;
            case R.id.hot_tv:
                break;
            case R.id.newest_tv:
                break;
        }
    }

    /**
     * 下载音频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
            @Override
            public void onStart() {
                LogUtils.e("onStart: ");
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    showToast("下载中...");
                    mCircleProgressLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    mCircleProgress.setProgress(currentLength);
                });
            }

            @Override
            public void onFinish(String localPath) {
                mVideoPath = localPath;
                LogUtils.e("onFinish: " + localPath);
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载完成");
                        mCircleProgressLayout.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    showToast(erroInfo);
                    mCircleProgressLayout.setVisibility(View.GONE);
                });
            }
        });
    }

    @Override
    public void getAudioListSuccess(AudioListResponse audioListResponse) {
        LogUtils.d("audioListResponse:" + new Gson().toJson(audioListResponse));
        audioContentResponseList = audioListResponse.getData();
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
        if (page == 1) {
            if (audioContentResponseList.size() > 0) {
                mAudioAdapter.setNewData(audioContentResponseList);
                mAudioAdapter.loadMoreComplete();
            } else {
                //加载Empty布局
                mAudioAdapter.setEmptyView(mEmptyView);
            }
        } else {
            mAudioAdapter.addData(audioContentResponseList);
            mAudioAdapter.loadMoreComplete();
        }
    }

    private void onRequestAudioList() {
        VideoListRequest videoListRequest = new VideoListRequest();
        videoListRequest.categoryId = mType;
        videoListRequest.pageNo = page;
        videoListRequest.pageSize = Constant.PAGESIZE;
        mPresenter.onRequestAudioList(videoListRequest);
    }


    @Override
    public void onRefresh() {
        page = 1;
        onRequestAudioList();
    }

    @Override
    public void onLoadMoreRequested() {
        if (page == 1 && audioContentResponseList.size() < Constant.PAGESIZE) {
            mAudioAdapter.loadMoreEnd(true);
        } else {
            if (audioContentResponseList.size() < Constant.PAGESIZE) {
                mAudioAdapter.loadMoreEnd();
            } else {
                page++;
                onRequestAudioList();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeInjector() {
        DaggerAudioFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .audioModule(new AudioModule((AppCompatActivity) getActivity()))
                .audioFragmentModule(new AudioFragmentModule(this))
                .build().inject(this);
    }


}
