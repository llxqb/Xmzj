package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.AudioContentResponse;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.JzvdStdMp3;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 视频播放界面
 */
public class AudioPlayDetailActivity extends BaseActivity implements JzvdStdMp3.JzStdMp3Listener{

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.jzvdStdMp3)
    JzvdStdMp3 mJzvdStdMp3;
    private String urlPath;

    public static void start(Context context, AudioContentResponse audioContentResponse) {
        Intent intent = new Intent(context, AudioPlayDetailActivity.class);
        intent.putExtra("audioContentResponse", audioContentResponse);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio_play_detail);
    }

    @Override
    protected void initView() {
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);//播放填充满背景，不带黑色背景
        mJzvdStdMp3.setListener(this);
        if (getIntent() != null) {
            AudioContentResponse mAudioContentResponse = getIntent().getParcelableExtra("audioContentResponse");
            mCommonTitleTv.setText(mAudioContentResponse.title);
            urlPath = mAudioContentResponse.url;
//        Glide.with(this)
//                .load(pic)
//                .into(mJzvdStdMp3.thumbImageView);
            mJzvdStdMp3.setThumb1(this, R.mipmap.video02);
            mJzvdStdMp3.setUp(urlPath, "", Jzvd.SCREEN_NORMAL);
//            Glide.with(this).load(videoResponse.coverPic).into(mMyJzvdStd.thumbImageView);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void startBtnCLick() {
        String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
        LogUtils.e("localFilePath:" + localFilePath);
        if (!TextUtils.isEmpty(localFilePath)) {
            //本地有资源
            showToast("播放本地视频");
            mJzvdStdMp3.setUp(localFilePath, "", Jzvd.SCREEN_NORMAL);
        }
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
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


}
