package com.xmzj.mvp.ui.activity.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.di.components.DaggerVideoComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.VideoClassifyResponse;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.mvp.ui.adapter.VideoDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 视频详情  episode为列表显示activity
 */

public class VideoDetailEpisodeActivity extends BaseActivity implements VideoControl.VideoView {

    @Inject
    VideoControl.PresenterVideo mPresenter;
    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.episode_recycler_view)
    RecyclerView mEpisodeRecyclerView;
    private List<VideoInfoResponse.EpisodesBean> episodesBeanList = new ArrayList<>();
    private VideoDetailAdapter mVideoAdapter;
    private String mToken;


    public static void start(Context context, ArrayList<VideoInfoResponse.EpisodesBean> episodesBeanList, String title) {
        Intent intent = new Intent(context, VideoDetailEpisodeActivity.class);
        intent.putParcelableArrayListExtra("videoInfoResponse", episodesBeanList);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video_detail_episode);
        setStatusBar();
        initInjectData();
        mToken = mBuProcessor.getToken();
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            episodesBeanList = getIntent().getParcelableArrayListExtra("videoInfoResponse");
            String title = getIntent().getStringExtra("title");
            mCommonTitleTv.setText(title);
//            LogUtils.e("episodesBeanList:"+mVideoInfoResponse.getEpisodes().size());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mEpisodeRecyclerView.setLayoutManager(linearLayoutManager);
        mVideoAdapter = new VideoDetailAdapter(this, episodesBeanList, mImageLoaderHelper);
        mEpisodeRecyclerView.setAdapter(mVideoAdapter);

        mEpisodeRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VideoInfoResponse.EpisodesBean episodesBean = (VideoInfoResponse.EpisodesBean) adapter.getItem(position);
                if (view.getId() == R.id.item_video_detail_layout) {
                    assert episodesBean != null;
                    VideoDetailActivity.start(VideoDetailEpisodeActivity.this, episodesBean.getVideoId(),episodesBean.getId());
                }
            }
        });
    }

    /**
     * EpisodesBean转化为EpisodeBean
     */
    private VideoInfoResponse.EpisodeBean episodesBean2EpisodeBean(VideoInfoResponse.EpisodesBean episodesBean) {
        VideoInfoResponse.EpisodeBean episodeBean = new VideoInfoResponse.EpisodeBean();
        episodeBean.setId(episodesBean.getId());
        episodeBean.setCover(episodesBean.getCover());
        episodeBean.setCreateTime(episodesBean.getCreateTime());
        episodeBean.setDownloadUrl(episodesBean.getDownloadUrl());
        episodeBean.setInfo(episodesBean.getInfo());
        episodeBean.setNum(episodesBean.getNum());
        episodeBean.setSrc(episodesBean.getSrc());
        episodeBean.setSrcType(episodesBean.getSrcType());
        episodeBean.setTitle(episodesBean.getTitle());
        episodeBean.setVideoId(episodesBean.getVideoId());
        episodeBean.setIsCollect(episodesBean.isIsCollect());
        return episodeBean;
    }


    @Override
    protected void initData() {

    }

    private void onRequestVideoInfo(String videoId) {
        mPresenter.onRequestVideoInfo(videoId);
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getVideoClassifySuccess(VideoClassifyResponse videoClassifyResponse) {
    }

    @Override
    public void getVideoInfoSuccess(VideoInfoResponse videoInfoResponse) {
//        LogUtils.e("videoInfoResponse:" + new Gson().toJson(videoInfoResponse));
//        VideoInfoResponse.EpisodeBean episodeBean = videoInfoResponse.getEpisode();
//        episodesBeanList = videoInfoResponse.getEpisodes();//
//        if (!episodesBeanList.isEmpty()) {
//            mVideoAdapter.setNewData(episodesBeanList);
//        } else {
//            VideoDetailActivity.start(VideoDetailEpisodeActivity.this, episodeBean);
//            finish();
//        }

    }

    @Override
    public void getVideoCollectionSuccess() {

    }

    private void initInjectData() {
        DaggerVideoComponent.builder().appComponent(getAppComponent())
                .videoModule(new VideoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
