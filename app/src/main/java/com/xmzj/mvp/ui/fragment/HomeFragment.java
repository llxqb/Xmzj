package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.modules.HomeFragmentModule;
import com.xmzj.di.modules.MainModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;

import java.util.Objects;

/**
 * MessageFragment
 * 消息
 */

public class HomeFragment extends BaseFragment implements HomeFragmentControl.HomeView {


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeInjector();
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


    private void initializeInjector() {
//        DaggerHomeFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
//                .mainModule(new MainModule((AppCompatActivity) getActivity()))
//                .homeFragmentModule(new HomeFragmentModule(this))
//                .build().inject(this);
    }

}
