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

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.di.components.DaggerMineFragmentComponent;
import com.xmzj.di.modules.MainModule;
import com.xmzj.di.modules.MineFragmentModule;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.VersionUpdateResponse;
import com.xmzj.help.DialogFactory;
import com.xmzj.mvp.ui.activity.main.MainActivity;
import com.xmzj.mvp.ui.activity.main.MineFragmentControl;
import com.xmzj.mvp.ui.activity.user.PersonalInfoActivity;
import com.xmzj.mvp.utils.DataCleanManager;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.utils.SystemUtils;
import com.xmzj.mvp.utils.UpdateManager;
import com.xmzj.mvp.views.dialog.CommonDialog;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class MineFragment extends BaseFragment implements MineFragmentControl.MineView, CommonDialog.CommonDialogListener {

    @Inject
    MineFragmentControl.mineFragmentPresenter mPresenter;
    Unbinder unbinder;
    @BindView(R.id.logout_tv)
    TextView mLogoutTv;
    @BindView(R.id.clear_cache_tv)
    TextView mClearCacheTv;
    @BindView(R.id.version_update_tv)
    TextView mVersionUpdateTv;
    private int clickPos;//1 清除缓存  2 退出登录

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mime, container, false);
        initializeInjector();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mVersionUpdateTv.setText(SystemUtils.getVersionName(getActivity()));
    }

    @Override
    public void initData() {
        try {
            mClearCacheTv.setText(DataCleanManager.getTotalCacheSize(Objects.requireNonNull(getActivity())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.avatar_iv, R.id.clear_cache_ll, R.id.version_update_ll, R.id.logout_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatar_iv:
                startActivitys(PersonalInfoActivity.class);
                break;
            case R.id.clear_cache_ll:
                clickPos = 1;
                showClearCacheDialog();
                break;
            case R.id.version_update_ll:
                onRequestVersionUpdate();
                break;
            case R.id.logout_tv:
                clickPos = 2;
                showExitLoginDialog();
                break;
        }
    }

    /**
     * 检查版本更新
     */
    private void onRequestVersionUpdate() {
        mPresenter.onRequestVersionUpdate();
    }

    @Override
    public void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse) {
        LogUtils.e("versionUpdateResponse:" + new Gson().toJson(versionUpdateResponse));
        if (versionUpdateResponse != null) {
            if (Integer.parseInt(versionUpdateResponse.getData().getVersionNumber()) > SystemUtils.getVersionCode(getActivity())) {
                UpdateManager mUpdateManager = new UpdateManager(getActivity(), versionUpdateResponse.getData());
                mUpdateManager.checkUpdateInfo();
            } else {
                showToast("当前版本：" + SystemUtils.getVersionName(getActivity()));
            }
        }
    }

    private void showExitLoginDialog() {
        DialogFactory.showCommonFragmentDialog(getActivity(), this, "退出登录", "你确定要退出？", "取消", "确定");
    }

    @Override
    public void commonDialogBtnOkListener() {
        if (clickPos == 1) {//1 清除缓存  2 退出登录
            DataCleanManager.clearAllCache(Objects.requireNonNull(getActivity()));
            try {
                mClearCacheTv.setText(DataCleanManager.getTotalCacheSize(getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (clickPos == 2) {
            exitLogin();
        }
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

    private void showClearCacheDialog() {
        String cacheValue = mClearCacheTv.getText().toString();
        DialogFactory.showCommonFragmentDialog(getActivity(), this, "清除缓存", cacheValue, "取消", "确定");
    }

    private void initializeInjector() {
        DaggerMineFragmentComponent.builder().appComponent(((XmzjApp) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .mineFragmentModule(new MineFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
