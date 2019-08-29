package com.xmzj.mvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xmzj.R;

import cn.jzvd.JzvdStd;

/**
 * 饺子视频播放器
 */
public class MyJzvdStd extends JzvdStd {
    public MyJzvdStd(Context context) {
        super(context);
    }

    public MyJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private MyJzStdListener mMyJzStdListener;
    private int mFirstClick = 1;

    public void setListener(MyJzStdListener myJzStdListener) {
        mMyJzStdListener = myJzStdListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_jz_layout_std;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.start) {
            Log.i(TAG, "onClick: start button");
            if (mMyJzStdListener != null) {
                if (mFirstClick == 1) {
                    mFirstClick++;
                    mMyJzStdListener.startBtnCLick();
                }
            }
        }
        super.onClick(v);
    }

    public interface MyJzStdListener {
        void startBtnCLick();
    }
}
