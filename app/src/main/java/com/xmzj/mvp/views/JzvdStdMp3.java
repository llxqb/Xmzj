package com.xmzj.mvp.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xmzj.R;

import cn.jzvd.JzvdStd;

/**
 * 这个本质上就是播放的时候不隐藏缩略图
 */
public class JzvdStdMp3 extends JzvdStd {
    private String TAG = "JzvdStdMp3";
    private ObjectAnimator mAnimator;
    private Context mContext;
    private int mConnectBg;

    public JzvdStdMp3(Context context) {
        super(context);
    }

    public JzvdStdMp3(Context context, AttributeSet attrs) {
        super(context, attrs);
        //默认显示 底部时间
        bottomContainer.setVisibility(VISIBLE);
        mContext = context;
    }

    private JzStdMp3Listener mJzStdMp3Listener;

    public void setListener(JzStdMp3Listener jzStdMp3Listener) {
        mJzStdMp3Listener = jzStdMp3Listener;
    }

    public void setConnectBg(int connectBg) {
        mConnectBg = connectBg;
    }

    public interface JzStdMp3Listener {
        void downLoadBtnCLick();

        void connectionBtnCLick();

        void startPreIvClick();

        void startNextIvClick();
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_standard_mp3;
    }

    ImageView connectionIv;

    public void setThumb1(Context context, int url) {
        CircleImageView circleImageView = findViewById(R.id.thumb1);
        findViewById(R.id.layout_bottom).setVisibility(VISIBLE);
        ImageView downloadIv = findViewById(R.id.download_iv);
        downloadIv.setOnClickListener(this);
        connectionIv = findViewById(R.id.collection_iv);
        connectionIv.setOnClickListener(this);
        if (mConnectBg != 0) {
            connectionIv.setImageResource(mConnectBg);
        }
        findViewById(R.id.pre_iv).setOnClickListener(this);
        findViewById(R.id.next_iv).setOnClickListener(this);
        Glide.with(this)
                .load(url)
                .into(circleImageView);

        mAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0.0f, 360.0f);
        mAnimator.setDuration(8000);//设定转一圈的时间
        mAnimator.setRepeatCount(Animation.INFINITE);//设定无限循环
        mAnimator.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
        mAnimator.setInterpolator(new LinearInterpolator());// 匀速
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "state:" + state);
        if (v.getId() == cn.jzvd.R.id.thumb) {
            if ((state == STATE_PLAYING || state == STATE_PAUSE || state == STATE_AUTO_COMPLETE)) {
                onClickUiToggle();
            } else if (state == STATE_PREPARING) {
                Toast.makeText(mContext, "加载中...请稍后", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.fullscreen) {
            Log.i(TAG, "onClick: fullscreen");
        } else if (v.getId() == R.id.pre_iv) {
            //上一曲
            if (mJzStdMp3Listener != null) {
                mJzStdMp3Listener.startPreIvClick();
            }
        } else if (v.getId() == R.id.next_iv) {
            //下一曲
            if (mJzStdMp3Listener != null) {
                mJzStdMp3Listener.startNextIvClick();
            }
        } else if (v.getId() == R.id.download_iv) {
            Log.i(TAG, "onClick: download_iv");
            if (mJzStdMp3Listener != null) {
                mJzStdMp3Listener.downLoadBtnCLick();
            }
        } else if (v.getId() == R.id.collection_iv) {
            if (mJzStdMp3Listener != null) {
                mJzStdMp3Listener.connectionBtnCLick();
            }
        } else {
            super.onClick(v);
        }
    }

    //changeUiTo 真能能修改ui的方法
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
    }

    @Override
    public void changeUiToPreparing() {
        super.changeUiToPreparing();
    }

    @Override
    public void changeUiToPlayingShow() {
        Log.e(TAG, "changeUiToPlayingShow()");
        super.changeUiToPlayingShow();
        thumbImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void changeUiToPlayingClear() {
        Log.e(TAG, "changeUiToPlayingClear()");
        super.changeUiToPlayingClear();
        thumbImageView.setVisibility(View.VISIBLE);
        mAnimator.start();//动画开始
    }

    @Override
    public void changeUiToPauseShow() {
        Log.e(TAG, "changeUiToPauseShow()");
        super.changeUiToPauseShow();
        thumbImageView.setVisibility(View.VISIBLE);
        mAnimator.pause();//动画暂停
    }

    @Override
    public void changeUiToPauseClear() {
        Log.e(TAG, "changeUiToPauseClear()");
        super.changeUiToPauseClear();
        thumbImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void changeUiToComplete() {
        Log.e(TAG, "changeUiToComplete()");
        super.changeUiToComplete();
//        mAnimator.resume();//动画重新开始
        mAnimator.pause();
        onClickUiToggle();
    }

    @Override
    public void changeUiToError() {
        super.changeUiToError();
    }
}
