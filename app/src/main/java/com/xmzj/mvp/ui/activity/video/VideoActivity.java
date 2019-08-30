package com.xmzj.mvp.ui.activity.video;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.mvp.ui.adapter.AudioPageAdapter;
import com.xmzj.mvp.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.upload_history_iv)
    ImageView mUploadHistoryIv;
    @BindView(R.id.play_history_iv)
    ImageView mPlayHistoryIv;
    @BindView(R.id.search_iv)
    ImageView mSearchIv;
    @BindView(R.id.search_content_et)
    EditText mSearchContentEt;
    @BindView(R.id.search_content_rl)
    RelativeLayout mSearchContentRl;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        String[] titleString = {"元音老人视频", "齐老师视频", "心密师兄"};
        for (int i = 0; i < titleString.length; i++) {
            fragmentList.add(VideoFragment.getInstance(i));
        }
        mViewPager.setAdapter(new AudioPageAdapter(getSupportFragmentManager(), fragmentList, titleString));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.back_iv, R.id.upload_history_iv, R.id.play_history_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.upload_history_iv:
                showToast("缓存");
                break;
            case R.id.play_history_iv:
                showToast("播放历史");
                break;
        }
    }
}