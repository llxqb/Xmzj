package com.xmzj.mvp.ui.activity.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmzj.R;
import com.xmzj.di.components.DaggerVideoComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.CommentResponse;
import com.xmzj.entity.response.VideoClassifyResponse;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.KbWithWordsCircleProgressBar;
import com.xmzj.mvp.views.MyJzvdStd;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoDetailActivity extends BaseActivity implements MyJzvdStd.MyJzStdListener, VideoControl.VideoView {
    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_iv_right)
    ImageView mCommonIvRight;
    @BindView(R.id.myJzvdStd)
    MyJzvdStd mMyJzvdStd;
    @BindView(R.id.web_view_rl)
    RelativeLayout mWebViewRl;
    @BindView(R.id.video_webview)
    WebView mWebView;
    @BindView(R.id.quanping_iv)
    ImageView mQuanPingIv;
    @BindView(R.id.collection_tv)
    TextView mCollectionTv;
    @BindView(R.id.download_tv)
    TextView mDownloadTv;
    @BindView(R.id.share_tv)
    TextView mShareTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl_circle_progress)
    FrameLayout mCircleProgressLayout;
    @BindView(R.id.circle_progress)
    KbWithWordsCircleProgressBar mCircleProgress;
    /**
     * 视频url路径
     */
    String urlPath;
    /**
     * 下载到本地视频路径
     */
    String mVideoPath;
    VideoInfoResponse.EpisodeBean mEpisodeBean;

    private List<CommentResponse> commentResponseList = new ArrayList<>();

    @Inject
    VideoControl.PresenterVideo mPresenter;

    public static void start(Context context, VideoInfoResponse.EpisodeBean episodeBean) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("episodeBean", episodeBean);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video_detail);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        mMyJzvdStd.setListener(this);
        if (getIntent() != null) {
            mEpisodeBean = getIntent().getParcelableExtra("episodeBean");
//            onRequestVideoInfo(mVideoId);
            mCommonTitleTv.setText(mEpisodeBean.getTitle());
            Glide.with(this).load(mEpisodeBean.getCover()).into(mMyJzvdStd.thumbImageView);
            urlPath = mEpisodeBean.getDownloadUrl();
            LogUtils.e("urlPath:" + urlPath);
            if (!TextUtils.isEmpty(urlPath)) {
                if (urlPath.contains(".html")) {
                    mWebViewRl.setVisibility(View.VISIBLE);
                    mMyJzvdStd.setVisibility(View.GONE);
                    initWebView(urlPath);
                } else if (urlPath.contains(".mp4")) {
                    mMyJzvdStd.setVisibility(View.VISIBLE);
                    mWebViewRl.setVisibility(View.GONE);
                    String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
                    if (!TextUtils.isEmpty(localFilePath)) {
                        setDownLoadColor();
                    }
                }
            } else {
                showToast("播放路径不正确 ");
            }
        }
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        VideoCommentAdapter mVideoCommentAdapter = new VideoCommentAdapter(this, commentResponseList);
//        mRecyclerView.setAdapter(mVideoCommentAdapter);
    }

    @Override
    protected void initData() {


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String urlPath) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        //自适应屏幕
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//设置同时加载Https和Http混合模式
        //支持屏幕缩放
        webSettings.setSupportZoom(false);
        //设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        mWebView.loadUrl(urlPath);//加载url
    }


    @OnClick({R.id.common_back, R.id.common_iv_right, R.id.collection_tv, R.id.download_tv, R.id.share_tv, R.id.quanping_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_iv_right:
                break;
            case R.id.collection_tv:
                setCollectionColor();
                onRequestCollection();
                break;
            case R.id.download_tv:
                if (!TextUtils.isEmpty(urlPath) && urlPath.toLowerCase().contains(".mp4")) {
                    if (TextUtils.isEmpty(DownloadUtil.checkFileIsExist(urlPath))) {
                        downloadVideo(); //处理具体下载过程
                    } else {
                        showToast("已下载");
                    }
                } else {
                    showToast("此格式不支持下载");
                }
                break;
            case R.id.share_tv:
                break;
            case R.id.quanping_iv://全屏
                if (mEpisodeBean != null) {
                    VideoPlayActivity.start(this, mEpisodeBean);
                }
                break;
        }
    }

    private void onRequestCollection() {
        mPresenter.onRequestVideoCollection(mEpisodeBean.getId());
    }

    private void setCollectionColor() {
        mCollectionTv.setTextColor(getResources().getColor(R.color.app_color));
        //获取更换的图片
        Drawable drawable = getResources().getDrawable(R.mipmap.detail_collectioned);
        //setBounds(x,y,width,height)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mCollectionTv.setCompoundDrawables(null, null, null, drawable);
    }


    private void setDownLoadColor() {
        mDownloadTv.setTextColor(getResources().getColor(R.color.app_color));
        //获取更换的图片
        Drawable drawable = getResources().getDrawable(R.mipmap.detail_downloaded);
        //setBounds(x,y,width,height)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mDownloadTv.setCompoundDrawables(null, null, null, drawable);
    }

    @Override
    public void getVideoClassifySuccess(VideoClassifyResponse videoClassifyResponse) {
    }

    /**
     * 获取视频详情
     */
    @Override
    public void getVideoInfoSuccess(VideoInfoResponse videoInfoResponse) {

    }

    /**
     * 下载视频文件
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
                runOnUiThread(() -> mCircleProgress.setProgress(currentLength));
            }

            @Override
            public void onFinish(String localPath) {
                mVideoPath = localPath;
                LogUtils.e("onFinish: " + localPath);
                runOnUiThread(() -> {
                    showToast("下载完成");
                    mCircleProgressLayout.setVisibility(View.GONE);
                    setDownLoadColor();
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                runOnUiThread(() -> {
                    mCircleProgressLayout.setVisibility(View.GONE);
                    showToast(erroInfo);
                });
            }
        });
    }

    @Override
    public void startBtnCLick() {
        String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
        LogUtils.e("localFilePath:" + localFilePath);
//        if (!TextUtils.isEmpty(localFilePath)) {
//            //本地有资源
//            showToast("播放本地视频");
//            mMyJzvdStd.setUp(localFilePath, videoResponse.title, Jzvd.SCREEN_NORMAL);
//        } else {
//            mMyJzvdStd.setUp(urlPath, videoResponse.title);
//        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.goOnPlayOnPause();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    private void initInjectData() {
        DaggerVideoComponent.builder().appComponent(getAppComponent())
                .videoModule(new VideoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
