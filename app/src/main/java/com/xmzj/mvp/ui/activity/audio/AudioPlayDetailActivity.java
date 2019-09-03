package com.xmzj.mvp.ui.activity.audio;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.AudioListResponse;
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
public class AudioPlayDetailActivity extends BaseActivity implements JzvdStdMp3.JzStdMp3Listener {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.jzvdStdMp3)
    JzvdStdMp3 mJzvdStdMp3;
    private String urlPath;
    private AudioListResponse.DataBean mDataBean;

    public static void start(Context context, AudioListResponse.DataBean dataBean) {
        Intent intent = new Intent(context, AudioPlayDetailActivity.class);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio_play_detail);
        setStatusBar();
    }

    @Override
    protected void initView() {
        mJzvdStdMp3.setListener(this);
        if (getIntent() != null) {
            mDataBean = getIntent().getParcelableExtra("dataBean");
            mCommonTitleTv.setText(mDataBean.getInfo());
            urlPath = mDataBean.getDownloadUrl();
            LogUtils.e("urlPath:" + urlPath);
//            Glide.with(this)
//                    .load(mDataBean.getCover())
//                    .into(mJzvdStdMp3.thumbImageView);
            mJzvdStdMp3.setThumb1(this, R.mipmap.audio_pic);
            mJzvdStdMp3.setUp(urlPath, mDataBean.getTitle(), Jzvd.SCREEN_NORMAL);
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
            mJzvdStdMp3.setUp(localFilePath, mDataBean.getTitle(), Jzvd.SCREEN_NORMAL);
        }
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        JzvdStd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);//播放填充满背景，不带黑色背景
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
