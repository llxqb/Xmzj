package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerHomeFragmentComponent;
import com.xmzj.di.modules.HomeFragmentModule;
import com.xmzj.di.modules.MainModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.entity.response.HomeFunctionResponse;
import com.xmzj.entity.response.HomeRecommendAudioResponse;
import com.xmzj.help.GlideImageLoader;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.ui.activity.audio.AudioActivity;
import com.xmzj.mvp.ui.activity.audio.AudioPlayDetailActivity;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;
import com.xmzj.mvp.ui.activity.video.VideoActivity;
import com.xmzj.mvp.ui.adapter.HomeRecommendAudioAdapter;
import com.xmzj.mvp.ui.adapter.HomeTopAdapter;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.utils.StatusBarUtil;
import com.xmzj.mvp.views.KbWithWordsCircleProgressBar;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class HomeFragment extends BaseFragment implements HomeFragmentControl.HomeView {

    @Inject
    HomeFragmentControl.homeFragmentPresenter mPresenter;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.home_top_recycler_view)
    RecyclerView mHomeTopRecyclerView;
    @BindView(R.id.home_bottom_recycler_view)
    RecyclerView mHomeBottomRecyclerView;
    Unbinder unbinder;
    private String[] FunctionTextString = {"心密视频", "心密音频"};//"心密书库",, "心密互动", "心密论坛", "打座计时", "商城", "活动报名"
    private int[] FunctionImageeString = {R.mipmap.videos, R.mipmap.music};//R.mipmap.books, , R.mipmap.hudong, R.mipmap.luntan, R.mipmap.clock, R.mipmap.mall, R.mipmap.baoming
    private String[] BottomFunctionText = {"视频", "音乐", "书籍"};
    private int[] BottomFunctionImg = {R.mipmap.books, R.mipmap.videos, R.mipmap.music};
    private List<Integer> bannerImgList = new ArrayList<>();
    private List<HomeFunctionResponse> functionResponseList = new ArrayList<>();
    private HomeRecommendAudioAdapter mHomeRecommendAudioAdapter;
    private List<HomeRecommendAudioResponse.AudiosBean> audiosBeanList = new ArrayList<>();
    /**
     * 视频url路径
     */
    String urlPath;
    /**
     * 是否正在下载
     */
    private boolean isDownLoading;
    //adapter下载音频进度条
    private FrameLayout mCircleProgressLayout;
    private KbWithWordsCircleProgressBar mCircleProgress;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeInjector();
        unbinder = ButterKnife.bind(this, view);
        StatusBarUtil.setTransparentForImageView(getActivity(), null);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        HomeTopAdapter mHomeTopAdapter = new HomeTopAdapter(getActivity(), functionResponseList, mImageLoaderHelper);
        mHomeTopRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 2));
        mHomeTopRecyclerView.setAdapter(mHomeTopAdapter);
        mHomeTopAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeFunctionResponse homeFunctionResponse = (HomeFunctionResponse) adapter.getItem(position);
            assert homeFunctionResponse != null;
            if (view.getId() == R.id.home_top_item_ll) {
                if (position == 0) {
                    startActivitys(VideoActivity.class);
                } else if (position == 1) {
                    startActivitys(AudioActivity.class);
                }
            }
        });
        mHomeRecommendAudioAdapter = new HomeRecommendAudioAdapter(getActivity(), audiosBeanList);
        mHomeBottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeBottomRecyclerView.setAdapter(mHomeRecommendAudioAdapter);

        mHomeRecommendAudioAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeRecommendAudioResponse.AudiosBean audiosBean = (HomeRecommendAudioResponse.AudiosBean) adapter.getItem(position);
            mCircleProgressLayout = (FrameLayout) adapter.getViewByPosition(mHomeBottomRecyclerView, position, R.id.fl_circle_progress);
            mCircleProgress = (KbWithWordsCircleProgressBar) adapter.getViewByPosition(mHomeBottomRecyclerView, position, R.id.circle_progress);
            switch (view.getId()) {
                case R.id.upload_iv://下载
                    urlPath = audiosBean.getDownloadUrl();
                    if (!TextUtils.isEmpty(urlPath)) {
                        if (TextUtils.isEmpty(DownloadUtil.checkFileIsExist(urlPath))) {
                            downloadVideo(); //处理具体下载过程
                        } else {
                            if (isDownLoading) {
                                showToast("下载中");
                            } else {
                                showToast("已下载");
                            }
                        }
                    } else {
                        showToast("资源不存在");
                    }
                    break;
                case R.id.item_home_audio:
                    if (audiosBean != null) {
                        AudioListResponse.DataBean dataBean = new AudioListResponse.DataBean();
                        dataBean.setId(audiosBean.getId());
                        dataBean.setTitle(audiosBean.getTitle());
                        dataBean.setSrc(audiosBean.getSrc());
                        dataBean.setDownloadUrl(audiosBean.getDownloadUrl());
                        AudioPlayDetailActivity.start(getActivity(), dataBean);
                    }
                    break;
            }
        });

    }

    @Override
    public void initData() {
        mPresenter.onRequestRecommendAudio();
        initBanner();
        for (int i = 0; i < FunctionTextString.length; i++) {
            HomeFunctionResponse homeFunctionResponse = new HomeFunctionResponse();
            homeFunctionResponse.text = FunctionTextString[i];
            homeFunctionResponse.img = FunctionImageeString[i];
            functionResponseList.add(homeFunctionResponse);
        }
//        for (int i = 0; i < BottomFunctionText.length; i++) {
//            HomeBottomFunctionResponse homeBottomFunctionResponse = new HomeBottomFunctionResponse();
//            homeBottomFunctionResponse.text = BottomFunctionText[i];
//            homeBottomFunctionResponse.img = BottomFunctionImg[i];
//            bottomFunctionResponseList.add(homeBottomFunctionResponse);
//        }
    }

    /**
     * banner
     */
    private void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        int bannerImg1 = R.mipmap.banner1;
        int bannerImg2 = R.mipmap.banner2;
        bannerImgList.add(bannerImg1);
        bannerImgList.add(bannerImg2);
        mBanner.setImages(bannerImgList);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    @Override
    public void getRecommendAudio(HomeRecommendAudioResponse homeRecommendAudioResponse) {
        mHomeRecommendAudioAdapter.setNewData(homeRecommendAudioResponse.getAudios());
    }


    /**
     * 下载音频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
            @Override
            public void onStart() {
                LogUtils.e("onStart: ");
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    showToast("下载中...");
                    isDownLoading = true;
                    mCircleProgressLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    mCircleProgress.setProgress(currentLength);
                });
            }

            @Override
            public void onFinish(String localPath) {
                //下载到本地视频路径 localPath
                LogUtils.e("onFinish: " + localPath);
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载完成");
                        isDownLoading = false;
                        mCircleProgressLayout.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    showToast("下载失败");
                    isDownLoading = false;
                    mCircleProgressLayout.setVisibility(View.GONE);
                });
            }
        });
    }

    private void initializeInjector() {
        DaggerHomeFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .homeFragmentModule(new HomeFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
