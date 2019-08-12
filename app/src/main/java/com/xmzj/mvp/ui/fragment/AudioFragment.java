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

public class AudioFragment extends BaseFragment {

    @BindView(R.id.fragment_view_pager)
    ViewPager mFragmentViewPager;
    @BindView(R.id.fragment_tab_layout)
    TabLayout mFragmentTabLayout;
    Unbinder unbinder;
    private int mType;

    public static AudioFragment getInstance(int type) {
        AudioFragment fragment = new AudioFragment();
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
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
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
                titleString = new String[]{"全部", "因果", "出离", "觉悟"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(AudioFragmentFragment.getInstance(i));
                }
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
                break;
            case 1:
                fragmentList.clear();
                titleString = new String[]{"全部", "因果", "出离", "觉悟"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(AudioFragmentFragment.getInstance(i));
                }
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
                break;
            case 2:
                fragmentList.clear();
                titleString = new String[]{"全部", "基本教理", "心经", "金刚经", "楞严经", "佛心经", "其它"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(AudioFragmentFragment.getInstance(i));
                }
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
                break;
            case 3:
                fragmentList.clear();
                titleString = new String[]{"全部", "认识心密", "百坐", "千坐", "打七打九", "方便与其它", "六字大明咒"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(AudioFragmentFragment.getInstance(i));
                }
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
                break;
            case 4:
                fragmentList.clear();
                titleString = new String[]{"全部", "明心见性", "除习与妙用", "以禅为体", "关于净土"};
                for (int i = 0; i < titleString.length; i++) {
                    fragmentList.add(AudioFragmentFragment.getInstance(i));
                }
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
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
