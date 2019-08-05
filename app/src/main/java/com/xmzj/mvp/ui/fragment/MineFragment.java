package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;

/**
 * MessageFragment
 * 消息
 */

public class MineFragment extends BaseFragment {


    public static MineFragment newInstance() {
        return new MineFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mime, container, false);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


}
