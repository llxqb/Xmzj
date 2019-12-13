package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.di.components.DaggerAudioComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.AudioModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.AudioClassifyResponse;
import com.xmzj.entity.response.AudioDetailInfoResponse;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.JzvdStdMp3;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 视频播放界面
 */
public class AudioPlayDetailActivity extends BaseActivity implements JzvdStdMp3.JzStdMp3Listener, AudioControl.AudioView {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.jzvdStdMp3)
    JzvdStdMp3 mJzvdStdMp3;
    private String urlPath;
    /**
     * 是否正在下载
     */
    private boolean isDownLoading;

    AudioDetailInfoResponse mAudioDetailInfoResponse;
    @Inject
    AudioControl.PresenterAudio mPresenter;

    public static void start(Context context, AudioListResponse.DataBean dataBean) {
        Intent intent = new Intent(context, AudioPlayDetailActivity.class);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio_play_detail);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        mJzvdStdMp3.setListener(this);
        if (getIntent() != null) {
            AudioListResponse.DataBean mDataBean = getIntent().getParcelableExtra("dataBean");
            onRequestAudioDetailInfo(mDataBean.getId());
//            Glide.with(this)
//                    .load(mDataBean.getCover())
//                    .into(mJzvdStdMp3.thumbImageView);
//            Glide.with(this).load(videoResponse.coverPic).into(mMyJzvdStd.thumbImageView);
        }
    }

    @Override
    protected void initData() {
    }


    @Override
    public void downLoadBtnCLick() {
        String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
        if (!TextUtils.isEmpty(localFilePath)) {
            //本地有资源
            if (isDownLoading) {
                showToast("下载中");
            } else {
                showToast("已下载");
            }
        } else {
            downloadVideo();
        }
    }

    /**
     * 收藏
     */
    @Override
    public void connectionBtnCLick() {
        mPresenter.onRequestAudioConnection(mAudioDetailInfoResponse.getId());
    }

    @Override
    public void startPreIvClick() {
        onRequestAudioDetailInfo(mAudioDetailInfoResponse.getPrev().getId());
    }

    @Override
    public void startNextIvClick() {
        onRequestAudioDetailInfo(mAudioDetailInfoResponse.getNext().getId());
    }

    @Override
    public void autoPlayNext() {
        onRequestAudioDetailInfo(mAudioDetailInfoResponse.getNext().getId());
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void getAudioClassifySuccess(AudioClassifyResponse audioClassifyResponse) {
    }

    /**
     * 请求音频详情
     */
    private void onRequestAudioDetailInfo(String id) {
        mPresenter.onRequestAudioDetailInfo(id);
    }

    @Override
    public void getAudioDetailInfoSuccess(AudioDetailInfoResponse audioDetailInfoResponse) {
        LogUtils.e("audioDetailInfoResponse:" + new Gson().toJson(audioDetailInfoResponse));
        if (audioDetailInfoResponse.isIsCollect()) {
            mJzvdStdMp3.setConnectBg(R.mipmap.connectioned_audio);
        } else {
            mJzvdStdMp3.setConnectBg(R.mipmap.connection_audio);
        }
        mAudioDetailInfoResponse = audioDetailInfoResponse;
        mCommonTitleTv.setText(audioDetailInfoResponse.getInfo());
        if (!TextUtils.isEmpty(audioDetailInfoResponse.getDownloadUrl()) && !audioDetailInfoResponse.getDownloadUrl().contains("/")) {
            urlPath = "https://www.xinmizj.com/res/audio/src/" + audioDetailInfoResponse.getDownloadUrl();
        } else {
            urlPath = audioDetailInfoResponse.getDownloadUrl();
        }
        initPlay();
    }

    private void initPlay() {
        String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
        if (!TextUtils.isEmpty(localFilePath)) {
            //本地有资源
            showToast("资源已下载");
            mJzvdStdMp3.setUp(localFilePath, mAudioDetailInfoResponse.getTitle(), Jzvd.SCREEN_NORMAL);
            mJzvdStdMp3.setThumb1(this, R.mipmap.audio_pic);
            mJzvdStdMp3.startVideo();
        } else {
            if (!TextUtils.isEmpty(urlPath)) {
                mJzvdStdMp3.setUp(urlPath, mAudioDetailInfoResponse.getTitle(), Jzvd.SCREEN_NORMAL);
                mJzvdStdMp3.setThumb1(this, R.mipmap.audio_pic);
                mJzvdStdMp3.startVideo();
            } else {
                showToast("播放路径不正确 ");
            }
        }
    }


    @Override
    public void getAudioConnectionSuccess() {
        showToast("收藏成功");
        mJzvdStdMp3.setConnectBg(R.mipmap.connectioned_audio);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mJzvdStdMp3.setBackgroundResource(R.color.app_bg_color);
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

    /**
     * 下载音频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
            @Override
            public void onStart() {
                isDownLoading = true;
                runOnUiThread(() -> {
                    showToast("下载中...");
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                runOnUiThread(() -> {
                });
            }

            @Override
            public void onFinish(String localPath) {
                isDownLoading = false;
                runOnUiThread(() -> showToast("下载完成"));
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                isDownLoading = false;
                runOnUiThread(() -> {
                    showToast("下载失败");
                });
            }
        });
    }


    private void initInjectData() {
        DaggerAudioComponent.builder().appComponent(getAppComponent())
                .audioModule(new AudioModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
