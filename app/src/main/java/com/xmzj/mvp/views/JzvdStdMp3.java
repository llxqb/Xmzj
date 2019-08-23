package com.xmzj.mvp.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;
import com.xmzj.R;

import cn.jzvd.JzvdStd;

/**
 * 这个本质上就是播放的时候不隐藏缩略图
 */
public class JzvdStdMp3 extends JzvdStd {
    private ObjectAnimator mAnimator;

    public JzvdStdMp3(Context context) {
        super(context);
    }

    public JzvdStdMp3(Context context, AttributeSet attrs) {
        super(context, attrs);
        //默认显示 底部时间
        bottomContainer.setVisibility(VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_standard_mp3;
    }

    public void setThumb1(Context context, int url) {

        CircleImageView  circleImageView = findViewById(R.id.thumb1);
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
        Log.e("ddd", "state:" + state);
        if (v.getId() == cn.jzvd.R.id.thumb &&
                (state == STATE_PLAYING ||
                        state == STATE_PAUSE)) {
            onClickUiToggle();
        } else if (v.getId() == R.id.fullscreen) {

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
        Log.e("ddd", "changeUiToPlayingShow()");
        super.changeUiToPlayingShow();
        thumbImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void changeUiToPlayingClear() {
        Log.e("ddd", "changeUiToPlayingClear()");
        super.changeUiToPlayingClear();
        thumbImageView.setVisibility(View.VISIBLE);
        mAnimator.start();//动画开始
    }

    @Override
    public void changeUiToPauseShow() {
        Log.e("ddd", "changeUiToPauseShow()");
        super.changeUiToPauseShow();
        thumbImageView.setVisibility(View.VISIBLE);
        mAnimator.pause();//动画暂停
    }

    @Override
    public void changeUiToPauseClear() {
        Log.e("ddd", "changeUiToPauseClear()");
        super.changeUiToPauseClear();
        thumbImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void changeUiToComplete() {
        Log.e("ddd", "changeUiToComplete()");
        super.changeUiToComplete();
        mAnimator.resume();//动画重新开始
    }

    @Override
    public void changeUiToError() {
        super.changeUiToError();
    }
}
