package com.xmzj.mvp.ui.activity.audio;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.di.components.DaggerAudioComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.AudioModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.AudioClassifyResponse;
import com.xmzj.entity.response.AudioDetailInfoResponse;
import com.xmzj.mvp.ui.activity.search.SearchAudioActivity;
import com.xmzj.mvp.ui.adapter.AudioPageAdapter;
import com.xmzj.mvp.ui.fragment.AudioFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AudioActivity extends BaseActivity implements AudioControl.AudioView {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.upload_history_iv)
    ImageView mUploadHistoryIv;
    @BindView(R.id.play_history_iv)
    ImageView mPlayHistoryIv;
    @BindView(R.id.search_content_tv)
    TextView mSearchContentTv;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    List<String> titleString = new ArrayList<>();
    @Inject
    AudioControl.PresenterAudio mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mPresenter.onRequestAudioClassify();
    }


    @OnClick({R.id.back_iv, R.id.upload_history_iv, R.id.play_history_iv, R.id.search_content_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.upload_history_iv:
                break;
            case R.id.play_history_iv:
                break;
            case R.id.search_content_rl:
                startActivitys(SearchAudioActivity.class);
                break;
        }
    }

    @Override
    public void getAudioClassifySuccess(AudioClassifyResponse audioClassifyResponse) {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < audioClassifyResponse.getData().size(); i++) {
            AudioClassifyResponse.DataBean dataBean = audioClassifyResponse.getData().get(i);
            titleString.add(dataBean.getName());
            fragmentList.add(AudioFragment.getInstance(dataBean));
        }
        if (!fragmentList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(fragmentList.size()-1);
            mViewPager.setAdapter(new AudioPageAdapter(getSupportFragmentManager(), fragmentList, titleString));
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    @Override
    public void getAudioDetailInfoSuccess(AudioDetailInfoResponse audioDetailInfoResponse) {
    }

    @Override
    public void getAudioConnectionSuccess() {
    }

    private void initInjectData() {
        DaggerAudioComponent.builder().appComponent(getAppComponent())
                .audioModule(new AudioModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
