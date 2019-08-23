package com.xmzj.mvp.ui.activity.video;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.VideoResponse;
import com.xmzj.mvp.views.MyJzvdStd;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoDetailActivity extends BaseActivity {
    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_iv_right)
    ImageView mCommonIvRight;
    @BindView(R.id.myJzvdStd)
    MyJzvdStd mMyJzvdStd;

    public static void start(Context context, VideoResponse videoResponse) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("videoResponse", videoResponse);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video_detail);
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            VideoResponse videoResponse = getIntent().getParcelableExtra("videoResponse");
            mCommonTitleTv.setText(videoResponse.author);
            mMyJzvdStd.setUp(videoResponse.url, "");
//            PicUtils.loadVideoScreenshot(mContext, coverValue, mJzVideo.thumbImageView, 0, false);//截取第一帧作为封面
            Glide.with(this).load(videoResponse.coverPic).into(mMyJzvdStd.thumbImageView);
        }
    }

    @Override
    protected void initData() {
    }


    @OnClick({R.id.common_back, R.id.common_iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_iv_right:
                break;
        }
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
