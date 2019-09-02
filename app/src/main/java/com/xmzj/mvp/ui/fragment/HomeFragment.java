package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerHomeFragmentComponent;
import com.xmzj.di.modules.HomeFragmentModule;
import com.xmzj.di.modules.MainModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.HomeBottomFunctionResponse;
import com.xmzj.entity.response.HomeFunctionResponse;
import com.xmzj.help.GlideImageLoader;
import com.xmzj.mvp.ui.activity.audio.AudioActivity;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;
import com.xmzj.mvp.ui.activity.video.VideoActivity;
import com.xmzj.mvp.ui.adapter.HomeBottomAdapter;
import com.xmzj.mvp.ui.adapter.HomeTopAdapter;
import com.xmzj.mvp.utils.StatusBarUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class HomeFragment extends BaseFragment implements HomeFragmentControl.HomeView {


    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.home_top_recycler_view)
    RecyclerView mHomeTopRecyclerView;
    @BindView(R.id.home_bottom_recycler_view)
    RecyclerView mHomeBottomRecyclerView;
    Unbinder unbinder;
    private String[] FunctionTextString = {"心密书库", "心密视频", "心密音频", "心密互动", "心密论坛", "打座计时", "商城", "活动报名"};
    private int[] FunctionImageeString = {R.mipmap.books, R.mipmap.videos, R.mipmap.music, R.mipmap.hudong, R.mipmap.luntan, R.mipmap.clock, R.mipmap.mall, R.mipmap.baoming};
    private String[] BottomFunctionText = {"视频", "音乐", "书籍"};
    private int[] BottomFunctionImg = {R.mipmap.books, R.mipmap.videos, R.mipmap.music};
    private List<String> bannerImgList = new ArrayList<>();
    private List<HomeFunctionResponse> functionResponseList = new ArrayList<>();
    private List<HomeBottomFunctionResponse> bottomFunctionResponseList = new ArrayList<>();

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mHomeTopRecyclerView.setLayoutManager(gridLayoutManager);
        HomeTopAdapter mHomeTopAdapter = new HomeTopAdapter(getActivity(), functionResponseList, mImageLoaderHelper);
        mHomeTopRecyclerView.setAdapter(mHomeTopAdapter);
        mHomeTopAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeFunctionResponse homeFunctionResponse = (HomeFunctionResponse) adapter.getItem(position);
            assert homeFunctionResponse != null;
            showToast(homeFunctionResponse.text);
            if (view.getId() == R.id.home_top_item_ll) {
                if (position == 2) {
                    startActivitys(AudioActivity.class);
                } else if (position == 1) {
                    startActivitys(VideoActivity.class);
                }
            }
        });
        mHomeBottomRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        HomeBottomAdapter mHomeBottomAdapter = new HomeBottomAdapter(getActivity(), bottomFunctionResponseList, mImageLoaderHelper);
        mHomeBottomRecyclerView.setAdapter(mHomeBottomAdapter);

    }

    @Override
    public void initData() {
        initBanner();
        for (int i = 0; i < FunctionTextString.length; i++) {
            HomeFunctionResponse homeFunctionResponse = new HomeFunctionResponse();
            homeFunctionResponse.text = FunctionTextString[i];
            homeFunctionResponse.img = FunctionImageeString[i];
            functionResponseList.add(homeFunctionResponse);
        }
        for (int i = 0; i < BottomFunctionText.length; i++) {
            HomeBottomFunctionResponse homeBottomFunctionResponse = new HomeBottomFunctionResponse();
            homeBottomFunctionResponse.text = BottomFunctionText[i];
            homeBottomFunctionResponse.img = BottomFunctionImg[i];
            bottomFunctionResponseList.add(homeBottomFunctionResponse);
        }
    }

    /**
     * banner
     */
    private void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        String bannerImg1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564997869677&di=08d1d748d6cdbdb2e6077cc643cd61fe&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20141230%2Fshikurulaifozu_3798005.jpg";
        String bannerImg2 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1540334387,2375027868&fm=26&gp=0.jpg";
        bannerImgList.add(bannerImg1);
        bannerImgList.add(bannerImg2);
        mBanner.setImages(bannerImgList);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
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
