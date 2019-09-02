package com.xmzj.mvp.ui.activity.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.mvp.utils.LogUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class VideoPlayActivity extends BaseActivity {

    @BindView(R.id.video_play_webview)
    WebView mVideoPlayWebview;
    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    @Inject
    VideoControl.PresenterVideo mPresenter;

    public static void start(Context context, VideoInfoResponse.EpisodeBean episodeBean) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra("episodeBean", episodeBean);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        setStatusBar();
//        initInjectData();
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            VideoInfoResponse.EpisodeBean episodeBean = getIntent().getParcelableExtra("episodeBean");
            String urlPath = episodeBean.getDownloadUrl();
            LogUtils.e("urlPath:" + urlPath);
            if (!TextUtils.isEmpty(urlPath) && urlPath.contains(".html")) {
                initWebView(urlPath);
            }
        }
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String urlPath) {
        mProgressbar.setMax(100);
        WebSettings webSettings = mVideoPlayWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        mVideoPlayWebview.setWebChromeClient(new WebChromeViewClient());
        //自适应屏幕
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//设置同时加载Https和Http混合模式
        //支持屏幕缩放
        webSettings.setSupportZoom(false);
        //设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        mVideoPlayWebview.loadUrl(urlPath);//加载url
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private class WebChromeViewClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressbar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressbar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }


//    private void initInjectData() {
//        DaggerVideoComponent.builder().appComponent(getAppComponent())
//                .videoModule(new VideoModule(this, this))
//                .activityModule(new ActivityModule(this)).build().inject(this);
//    }

}
