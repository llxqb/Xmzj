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
import com.xmzj.entity.response.VideoClassifyResponse;
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
    private List<String> titleString = new ArrayList<>();


    public static VideoFragment getInstance(VideoClassifyResponse.DataBean dataBean) {
        VideoFragment fragment = new VideoFragment();
        Bundle bd = new Bundle();
        bd.putParcelable("dataBean", dataBean);
        fragment.setArguments(bd);
        return fragment;
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


    @Override
    public void initView() {
        //    private String mType;
        List<Fragment> fragmentList = new ArrayList<>();
        if (getArguments() != null) {
            VideoClassifyResponse.DataBean mDataBean = getArguments().getParcelable("dataBean");
            if (mDataBean == null) return;
            for (VideoClassifyResponse.DataBean.ChildsBean childsBean : mDataBean.getChilds()) {
                titleString.add(childsBean.getName());
                fragmentList.add(VideoFragmentFragment.getInstance(childsBean.getId()));//传子分类id
            }
            mViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
            mTabLayout.setupWithViewPager(mViewPager);
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
