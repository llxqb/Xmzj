package com.xmzj.mvp.ui.activity.video;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xmzj.R;
import com.xmzj.di.components.DaggerVideoComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.VideoClassifyResponse;
import com.xmzj.mvp.ui.adapter.AudioPageAdapter;
import com.xmzj.mvp.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity implements VideoControl.VideoView {

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
    List<String> titleString = new ArrayList<>();
    @Inject
    VideoControl.PresenterVideo mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        //{"元音老人视频", "齐老师视频", "心密师兄"}
    }

    @Override
    protected void initData() {
        mPresenter.onRequestVideoClassify();
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

    @Override
    public void getVideoClassifySuccess(VideoClassifyResponse videoClassifyResponse) {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i=0;i<videoClassifyResponse.getData().size();i++){
            VideoClassifyResponse.DataBean dataBean = videoClassifyResponse.getData().get(i);
            titleString.add(dataBean.getName());
            fragmentList.add(VideoFragment.getInstance(dataBean));
        }
        mViewPager.setAdapter(new AudioPageAdapter(getSupportFragmentManager(), fragmentList, titleString));
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private void initInjectData() {
        DaggerVideoComponent.builder().appComponent(getAppComponent())
                .videoModule(new VideoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
