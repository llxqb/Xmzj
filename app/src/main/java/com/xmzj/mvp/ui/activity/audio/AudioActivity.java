package com.xmzj.mvp.ui.activity.audio;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xmzj.R;
import com.xmzj.di.components.DaggerAudioComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.AudioModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.mvp.ui.adapter.AudioPageAdapter;
import com.xmzj.mvp.ui.fragment.AudioFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AudioActivity extends BaseActivity implements AudioControl.AudioView{

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
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        String[] titleString = {"佛教常识", "佛经", "心中心", "宗趣归宿","其它"};
        for (int i = 0; i < titleString.length; i++) {
            fragmentList.add(AudioFragment.getInstance(i));
        }
        mViewPager.setAdapter(new AudioPageAdapter(getSupportFragmentManager(), fragmentList, titleString));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
    }


    @OnClick({R.id.back_iv, R.id.upload_history_iv, R.id.play_history_iv, R.id.search_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.upload_history_iv:
                break;
            case R.id.play_history_iv:
                break;
            case R.id.search_iv:
                break;
        }
    }

    private void initInjectData() {
        DaggerAudioComponent.builder().appComponent(getAppComponent())
                .audioModule(new AudioModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
