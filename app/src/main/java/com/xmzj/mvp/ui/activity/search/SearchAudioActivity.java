package com.xmzj.mvp.ui.activity.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.ui.activity.audio.AudioPlayDetailActivity;
import com.xmzj.mvp.ui.adapter.AudioAdapter;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.KbWithWordsCircleProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchAudioActivity extends BaseActivity implements SearchControl.SearchView, BaseQuickAdapter.RequestLoadMoreListener {

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
    private List<AudioListResponse.DataBean> audioContentResponseList = new ArrayList<>();
    private AudioAdapter mAudioAdapter;
    private View mEmptyView;
    //adapter下载音频进度条
    private FrameLayout mCircleProgressLayout;
    private KbWithWordsCircleProgressBar mCircleProgress;
    /**
     * 视频url路径
     */
    String urlPath;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAudioAdapter = new AudioAdapter(this, audioContentResponseList);
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
                        AudioPlayDetailActivity.start(SearchAudioActivity.this, dataBean);
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
                onRequestAudioList(searchContent);
                break;
        }
    }

    /**
     * 搜索音频列表
     */
    private void onRequestAudioList(String keyword) {
        VideoListRequest videoListRequest = new VideoListRequest();
        videoListRequest.categoryId = "";
        videoListRequest.orderCol = "";
        videoListRequest.keyword = keyword;
        videoListRequest.pageNo = page;
        videoListRequest.pageSize = Constant.PAGESIZE;
        mPresenter.onRequestAudioList(videoListRequest);
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
                onRequestAudioList(mSearchContentEt.getText().toString());
            }
        }
    }

    @Override
    public void getVideoListSuccess(VideoListResponse response) {
    }

    @Override
    public void getAudioListSuccess(AudioListResponse audioListResponse) {
        LogUtils.d("audioListResponse:" + new Gson().toJson(audioListResponse));
        audioContentResponseList = audioListResponse.getData();
        if (page == 1) {
            if (!audioContentResponseList.isEmpty()) {
                mAudioAdapter.setNewData(audioContentResponseList);
                mAudioAdapter.loadMoreComplete();
            } else {
                //加载Empty布局
                mAudioAdapter.setNewData(null);
                mAudioAdapter.setEmptyView(mEmptyView);
            }
        } else {
            mAudioAdapter.addData(audioContentResponseList);
            mAudioAdapter.loadMoreComplete();
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
                runOnUiThread(() -> {
                    showToast("下载中...");
                    mCircleProgressLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                runOnUiThread(() -> {
                    mCircleProgress.setProgress(currentLength);
                });
            }

            @Override
            public void onFinish(String localPath) {
                runOnUiThread(() -> {
                    showToast("下载完成");
                    mCircleProgressLayout.setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                runOnUiThread(() -> {
                    showToast(erroInfo);
                    mCircleProgressLayout.setVisibility(View.GONE);
                });
            }
        });
    }

    private void initInjectData() {
        DaggerSearchComponent.builder().appComponent(getAppComponent())
                .searchModule(new SearchModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
