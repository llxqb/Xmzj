package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.AudioContentResponse;
import com.xmzj.mvp.views.JzvdStdMp3;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 视频播放界面
 */
public class AudioPlayDetailActivity extends BaseActivity {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.jzvdStdMp3)
    JzvdStdMp3 mJzvdStdMp3;

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
        if (getIntent() != null) {
            AudioContentResponse mAudioContentResponse = getIntent().getParcelableExtra("audioContentResponse");
            mCommonTitleTv.setText(mAudioContentResponse.title);
        }
        String mp3 = "https://www.xinmizj.com/res/audio/src/dsbushiczsh.mp3";
        String pic = "http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png";
        mJzvdStdMp3.setUp(mp3, "", Jzvd.SCREEN_NORMAL);
//        Glide.with(this)
//                .load(pic)
//                .into(mJzvdStdMp3.thumbImageView);
        mJzvdStdMp3.setThumb1(this, R.mipmap.video02);
    }

    @Override
    protected void initData() {

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
