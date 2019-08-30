package com.xmzj.mvp.ui.activity.register;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.di.components.DaggerRegisterComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.RegisterModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.ForgetPwdResponse;
import com.xmzj.entity.response.RegisterResponse;
import com.xmzj.mvp.utils.ValueUtil;
import com.xmzj.mvp.views.TimeButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity implements RegisterControl.RegisterView {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.forget_pwd_phone_et)
    EditText mForgetPwdPhoneEt;
    @BindView(R.id.forget_pwd_et)
    EditText mForgetPwdEt;
    @BindView(R.id.forget_pwd_verify_et)
    EditText mForgetPwdVerifyEt;
    @BindView(R.id.code_bt)
    TimeButton mCodeBt;
    @BindView(R.id.forget_pwd_btn)
    TextView mForgetPwdBtn;

    @Inject
    RegisterControl.PresenterRegister mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_forget_pwd);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText("忘记密码");
        mForgetPwdPhoneEt.addTextChangedListener(search_text_OnChange);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.common_back, R.id.code_bt, R.id.forget_pwd_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.code_bt:
                if (TextUtils.isEmpty(mForgetPwdPhoneEt.getText().toString())) {
                    showToast("手机号不能为空");
                    return;
                }
                if (verification()) {
                    mCodeBt.setRun(true);
                }
                break;
            case R.id.forget_pwd_btn:
                if (TextUtils.isEmpty(mForgetPwdPhoneEt.getText().toString())) {
                    showToast("手机号/邮箱不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mForgetPwdVerifyEt.getText().toString())) {
                    showToast("验证码不能为空");
                    return;
                }
                if (verification()) {
                    onRequestForgetPwd();
                }
                break;
        }
    }

    /**
     * 验证
     */
    private boolean verification() {
        if (TextUtils.isEmpty(mForgetPwdEt.getText().toString())) {
            showToast("新密码不能为空");
            return false;
        }
        return true;
    }


    /**
     * 请求 -忘记密码
     */
    private void onRequestForgetPwd() {
//        ForgetPwdRequest forgetPwdRequest = new ForgetPwdRequest();
//        mPresenter.onRequestForgetPwd(forgetPwdRequest);
        showToast("修改密码成功");
        finish();
    }

    /**
     * 注册成功
     */
    @Override
    public void getRegisterSuccess(RegisterResponse registerResponse) {
    }

    /**
     * 忘记密码修改成功
     */
    @Override
    public void getForgetPwdSuccess(ForgetPwdResponse forgetPwdResponse) {

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
        DaggerRegisterComponent.builder().appComponent(getAppComponent())
                .registerModule(new RegisterModule(ForgetPwdActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
