package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.VideoResponse;
import com.xmzj.help.GlideImageLoader;
import com.xmzj.mvp.ui.activity.video.VideoDetailActivity;
import com.xmzj.mvp.ui.adapter.VideoAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * VideoFragmentFragment
 */

public class VideoFragmentFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.video_banner)
    Banner mVideoBanner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<VideoResponse> videoResponseList = new ArrayList<>();
    /**
     * 视频一级标题筛选
     */
    private int mType;
    /**
     * 视频二级标题筛选
     */
    private int mType2;
    /**
     * banner集合
     */
    private List<String> bannerImgList = new ArrayList<>();

    public static VideoFragmentFragment getInstance(int type, int type2) {
        VideoFragmentFragment fragment = new VideoFragmentFragment();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        bd.putInt("type2", type2);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt("type");
            mType2 = getArguments().getInt("type2");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        VideoAdapter mVideoAdapter = new VideoAdapter(getActivity(), videoResponseList);
        mRecyclerView.setAdapter(mVideoAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VideoResponse videoResponse = (VideoResponse) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.item_video_layout:
                        assert videoResponse != null;
                        VideoDetailActivity.start(getActivity(), videoResponse);
                        break;
                    case R.id.upload_iv:
                        showToast("下载...");
                        break;
                    case R.id.share_iv:
                        showToast("分享...");
                        break;
                }
            }
        });
        //初始化banner
        initBanner();
    }

    @Override
    public void initData() {
        switch (mType) {
            case 0:
                if (mType2 == 0) {
                    for (int i = 0; i < 20; i++) {
                        VideoResponse videoResponse = new VideoResponse();
                        videoResponse.author = "元音老人";
                        videoResponse.title = "元音老人随缘问答" + i;
                        if (i % 3 == 0) {
                            videoResponse.coverPic = R.mipmap.video1;
                        } else if (i % 3 == 1) {
                            videoResponse.coverPic = R.mipmap.video2;
                        } else {
                            videoResponse.coverPic = R.mipmap.video3;
                        }
                        videoResponse.url = "http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4";
                        videoResponseList.add(videoResponse);
                    }
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }


    }


    /**
     * banner
     */
    private void initBanner() {
        //设置图片加载器
        mVideoBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        String bannerImg1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564997869677&di=08d1d748d6cdbdb2e6077cc643cd61fe&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20141230%2Fshikurulaifozu_3798005.jpg";
        String bannerImg2 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1540334387,2375027868&fm=26&gp=0.jpg";
        bannerImgList.add(bannerImg1);
        bannerImgList.add(bannerImg2);
        mVideoBanner.setImages(bannerImgList);
        //设置轮播时间
        mVideoBanner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        mVideoBanner.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
