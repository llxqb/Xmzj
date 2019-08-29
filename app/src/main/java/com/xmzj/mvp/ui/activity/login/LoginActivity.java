package com.xmzj.mvp.ui.activity.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xmzj.R;
import com.xmzj.di.components.DaggerLoginComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.LoginModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.LoginResponse;
import com.xmzj.entity.user.LoginUser;
import com.xmzj.mvp.ui.activity.main.MainActivity;
import com.xmzj.mvp.utils.PermissionUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginControl.LoginView {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.login_user_et)
    EditText mLoginUserEt;
    @BindView(R.id.login_pwd_et)
    EditText mLoginPwdEt;
    @BindView(R.id.login_btn)
    TextView mLoginBtn;
    @BindView(R.id.register_tv)
    TextView mRegisterTv;
    @BindView(R.id.forget_pwd_tv)
    TextView mForgetPwdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
        initInjectData();
    }

    @Override
    protected void initView() {
        mCommonBack.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        mLoginUserEt.setText("13262253731");
        mLoginPwdEt.setText("123456");
    }

    /**
     * 检查app 权限
     */
    @SuppressLint("CheckResult")
    private void checkPermissions() {
        RxPermissions mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(permission -> {
            if (permission) {
                //TODO 登录操作
                LoginUser loginUser = new LoginUser();
                loginUser.phone = mLoginUserEt.getText().toString();
                loginUser.pwd = mLoginPwdEt.getText().toString();
                mBuProcessor.setLoginUser(loginUser);
                startActivitys(MainActivity.class);
                finish();
            } else {
                showToast("请打开所有权限");
                PermissionUtils.goSetting(this); //跳转至当前app的权限设置界面
            }
        });
    }


    @OnClick({R.id.login_btn, R.id.register_tv, R.id.forget_pwd_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                checkPermissions();
                break;
            case R.id.register_tv:
                showToast("注册");
                break;
            case R.id.forget_pwd_tv:
                showToast("忘记密码");
                break;
        }
    }

    @Override
    public void getLoginSuccess(LoginResponse loginResponse) {

    }

    private void initInjectData() {
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(LoginActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
