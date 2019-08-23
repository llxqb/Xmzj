package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.mvp.ui.adapter.AudioPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * AudioFragment
 */

public class VideoFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private int mType;

    public static VideoFragment getInstance(int type) {
        VideoFragment fragment = new VideoFragment();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt("type");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    List<Fragment> fragmentList = new ArrayList<>();
    String[] titleString;

    @Override
    public void initView() {
        switch (mType) {
            case 0:
                fragmentList.clear();
                titleString = new String[]{"全部", "随缘问答", "各地开示", "讲经", "其它"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(VideoFragmentFragment.getInstance(mType, i));
                }
                mViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mTabLayout.setupWithViewPager(mViewPager);
                break;
            case 1:
                fragmentList.clear();
                titleString = new String[]{"全部", "灌顶答疑", "打七答疑", "其它"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(VideoFragmentFragment.getInstance(mType, i));
                }
                mViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mTabLayout.setupWithViewPager(mViewPager);
                break;
            case 2:
                fragmentList.clear();
                titleString = new String[]{"全部", "郭鸿雁老师视频", "其它"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(VideoFragmentFragment.getInstance(mType, i));
                }
                mViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mTabLayout.setupWithViewPager(mViewPager);
                break;
        }
    }

    @Override
    public void initData() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
