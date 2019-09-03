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
import com.xmzj.entity.response.AudioClassifyResponse;
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
    private List<String> titleString = new ArrayList<>();

    public static AudioFragment getInstance(AudioClassifyResponse.DataBean dataBean) {
        AudioFragment fragment = new AudioFragment();
        Bundle bd = new Bundle();
        bd.putParcelable("dataBean", dataBean);
        fragment.setArguments(bd);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        if (getArguments() != null) {
            AudioClassifyResponse.DataBean mDataBean = getArguments().getParcelable("dataBean");
            if (mDataBean == null) return;
            for (AudioClassifyResponse.DataBean.ChildsBean childsBean : mDataBean.getChilds()) {
                titleString.add(childsBean.getName());
                fragmentList.add(AudioFragmentFragment.getInstance(childsBean.getId()));//传子分类id
            }
            if(!fragmentList.isEmpty()){
                mFragmentViewPager.setAdapter(new AudioPageAdapter(getChildFragmentManager(), fragmentList, titleString));
                mFragmentViewPager.setOffscreenPageLimit(fragmentList.size()-1);
                mFragmentTabLayout.setupWithViewPager(mFragmentViewPager);
            }
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
