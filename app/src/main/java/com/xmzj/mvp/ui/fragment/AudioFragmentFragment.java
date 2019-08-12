package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *AudioFragmentFragment
 */

public class AudioFragmentFragment extends BaseFragment {

    @BindView(R.id.upload_all_tv)
    TextView mUploadAllTv;
    @BindView(R.id.hot_tv)
    TextView mHotTv;
    @BindView(R.id.newest_tv)
    TextView mNewestTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private int mType;

    public static AudioFragmentFragment getInstance(int type) {
        AudioFragmentFragment fragment = new AudioFragmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_fragment_audio, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
//        if(){
//
//        }
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.upload_all_tv, R.id.hot_tv, R.id.newest_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_all_tv:
                break;
            case R.id.hot_tv:
                break;
            case R.id.newest_tv:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
