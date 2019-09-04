package com.xmzj.mvp.ui.activity.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xmzj.R;
import com.xmzj.di.components.DaggerLoginComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.LoginModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.LoginRequest;
import com.xmzj.entity.user.LoginUser;
import com.xmzj.mvp.ui.activity.main.MainActivity;
import com.xmzj.mvp.ui.activity.register.ForgetPwdActivity;
import com.xmzj.mvp.ui.activity.register.RegisterActivity;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.utils.PermissionUtils;
import com.xmzj.mvp.utils.ValueUtil;
import com.xmzj.mvp.views.TimeButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginControl.LoginView {

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
    @BindView(R.id.verify_login_tab_layout)
    LinearLayout mVerifyLoginTabLayout;
    @BindView(R.id.verify_login_tab_view)
    View mVerifyLoginTabView;
    @BindView(R.id.pwd_login_tab_layout)
    LinearLayout mPwdLoginTabLayout;
    @BindView(R.id.pwd_login_tab_view)
    View mPwdLoginTabView;
    @BindView(R.id.login_phone_et)
    EditText mLoginPhoneEt;
    @BindView(R.id.login_verify_et)
    EditText mLoginVerifyEt;
    @BindView(R.id.code_bt)
    TimeButton mCodeBt;
    @BindView(R.id.verify_Login_layout)
    LinearLayout mVerifyLoginLayout;
    @BindView(R.id.pwd_Login_layout)
    LinearLayout mPwdLoginLayout;
    /**
     * 0 :  验证码登录
     * 1 ： 密码登录
     */
    private int loginType;

    @Inject
    LoginControl.PresenterLogin mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
        setStatusBarGray();
        initInjectData();
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText("登录");
//        mLoginPhoneEt.addTextChangedListener(search_text_OnChange);
    }

    @Override
    protected void initData() {
        mLoginPhoneEt.setText("13262253731");
        mLoginUserEt.setText("13262253731");
        mLoginPwdEt.setText("123456");
    }

    @OnClick({R.id.login_btn, R.id.register_tv, R.id.forget_pwd_tv, R.id.verify_login_tab_layout, R.id.pwd_login_tab_layout, R.id.code_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_login_tab_layout://验证码登录tab选择
                loginType = 0;
                mVerifyLoginTabView.setVisibility(View.VISIBLE);
                mPwdLoginTabView.setVisibility(View.GONE);
                mVerifyLoginLayout.setVisibility(View.VISIBLE);
                mPwdLoginLayout.setVisibility(View.GONE);
                break;
            case R.id.pwd_login_tab_layout://密码登录tab选择
                loginType = 1;
                mVerifyLoginTabView.setVisibility(View.GONE);
                mPwdLoginTabView.setVisibility(View.VISIBLE);
                mVerifyLoginLayout.setVisibility(View.GONE);
                mPwdLoginLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.code_bt:
                if (TextUtils.isEmpty(mLoginPhoneEt.getText().toString())) {
                    showToast("手机号/邮箱不能为空");
                    return;
                }
                mCodeBt.setRun(true);
                mCodeBt.setAfterBg(R.drawable.verify_text_background);
                onRequestVerifyCode(mLoginPhoneEt.getText().toString(), Constant.VerifyCode_LOGIN);
                break;
            case R.id.login_btn:
                if (verification()) {
                    checkPermissions();
                }
                break;
            case R.id.register_tv:
                startActivitys(RegisterActivity.class);
                break;
            case R.id.forget_pwd_tv:
                startActivitys(ForgetPwdActivity.class);
                break;
        }
    }

    private boolean verification() {
        if (loginType == 0) {
            if (TextUtils.isEmpty(mLoginPhoneEt.getText().toString())) {
                showToast("手机号/邮箱不能为空");
                return false;
            }
            if (TextUtils.isEmpty(mLoginVerifyEt.getText().toString())) {
                showToast("验证码不能为空");
                return false;
            }
        } else if (loginType == 1) {
            if (TextUtils.isEmpty(mLoginUserEt.getText().toString())) {
                showToast("账号不能为空");
                return false;
            }
            if (TextUtils.isEmpty(mLoginPwdEt.getText().toString())) {
                showToast("密码不能为空");
                return false;
            }
        }
        return true;
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
                onRequestLogin();
            } else {
                showToast("请打开所有权限");
                PermissionUtils.goSetting(this); //跳转至当前app的权限设置界面
            }
        });
    }

    /**
     * 获取验证码
     *
     * @param account 手机号码/邮箱
     * @param type    验证类型(注册：100001，重置密码：100002,登录：100003)
     */
    private void onRequestVerifyCode(String account, int type) {
        mPresenter.onRequestVerifyCode(account, type);
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void getVerifyCodeSuccess(String code) {
        LogUtils.e("code:" + code);
        if(!TextUtils.isEmpty(code)){
            if (ValueUtil.isValidityEmail(mLoginPhoneEt.getText().toString())) {
                showToast("获取验证码成功，请去邮箱查看");
            } else {
                mLoginVerifyEt.setText(code);
            }
        }else {
            showToast("操作过于频繁，请稍后再试");
        }

    }


    /**
     * 请求登录
     */
    private void onRequestLogin() {
        LoginRequest loginRequest = new LoginRequest();
        if (loginType == 0) {
            loginRequest.account = mLoginPhoneEt.getText().toString();
            loginRequest.code = mLoginVerifyEt.getText().toString();
        } else if (loginType == 1) {
            loginRequest.account = mLoginUserEt.getText().toString();
            loginRequest.pwd = mLoginPwdEt.getText().toString();
        }
        loginRequest.clientType = Constant.FROM;
        mPresenter.onRequestLogin(loginRequest);
    }


    @Override
    public void getLoginSuccess(String token) {
        LoginUser loginUser = new LoginUser();
        loginUser.token = token;
        if (loginType == 0) {
            loginUser.phoneOrMail = mLoginPhoneEt.getText().toString();
        } else {
            loginUser.username = mLoginUserEt.getText().toString();
            loginUser.pwd = mLoginPwdEt.getText().toString();
        }
        mBuProcessor.setLoginUser(loginUser);
        mSharePreferenceUtil.setData("token",token);
        startActivitys(MainActivity.class);
        finish();
    }


    public TextWatcher search_text_OnChange = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ValueUtil.isMobilePhone(s.toString())) {
                mCodeBt.setVisibility(View.VISIBLE);
            } else {
                mCodeBt.setVisibility(View.GONE);
            }
        }
    };


    private void initInjectData() {
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(LoginActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
