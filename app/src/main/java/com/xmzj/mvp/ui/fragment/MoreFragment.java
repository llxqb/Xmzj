package com.xmzj.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerMoreFragmentComponent;
import com.xmzj.di.modules.MainModule;
import com.xmzj.di.modules.MoreFragmentModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.constants.Constant;
import com.xmzj.help.DialogFactory;
import com.xmzj.mvp.ui.activity.main.MainActivity;
import com.xmzj.mvp.ui.activity.main.MoreFragmentControl;
import com.xmzj.mvp.views.dialog.CommonDialog;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class MoreFragment extends BaseFragment implements CommonDialog.CommonDialogListener , MoreFragmentControl.MoreFragmentView {


    @BindView(R.id.test)
    TextView mTest;
    @BindView(R.id.logout_tv)
    TextView mLogoutTv;
    Unbinder unbinder;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        unbinder = ButterKnife.bind(this, view);
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


    @OnClick({R.id.test, R.id.logout_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test:
                break;
            case R.id.logout_tv:
                showExitLoginDialog();
                break;
        }
    }

    private void showExitLoginDialog() {
        DialogFactory.showCommonDialogFragemnt(getActivity(), this, "你确定要退出？", Constant.DIALOG_FIVE);
    }

    @Override
    public void commonDialogBtnOkListener() {
        exitLogin();
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        mSharePreferenceUtil.clearData();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//表示 不创建新的实例activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//表示 移除该activity上面的activity
        intent.putExtra("exitLogin", true);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeInjector() {
        DaggerMoreFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .moreFragmentModule(new MoreFragmentModule(this))
                .build().inject(this);
    }

}
