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
import com.xmzj.entity.constants.Constant;
import com.xmzj.entity.request.RegisterRequest;
import com.xmzj.entity.request.VerifyCodeRequest;
import com.xmzj.mvp.utils.LogUtils;
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
//        mForgetPwdPhoneEt.addTextChangedListener(search_text_OnChange);
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
                String mForgetPwdPhoneEtValue = mForgetPwdPhoneEt.getText().toString();
                if (TextUtils.isEmpty(mForgetPwdPhoneEt.getText().toString())) {
                    showToast("手机号/邮箱不能为空");
                    return;
                }
                if (!ValueUtil.isValidityEmail(mForgetPwdPhoneEtValue) && !ValueUtil.isChinaPhoneLegal(mForgetPwdPhoneEtValue)) {
                    showToast("手机号/邮箱格式不正确");
                    return;
                }
                mCodeBt.setRun(true);
                mCodeBt.setAfterBg(R.drawable.verify_text_background);
                onRequestVerifyCode(mForgetPwdPhoneEtValue);
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
     * 获取验证码
     *
     * @param phoneNum 手机号码/邮箱
     *                 type    验证类型(注册：100001，重置密码：100002,登录：100003)
     */
    private void onRequestVerifyCode(String phoneNum) {
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest();
        if (ValueUtil.isValidityEmail(phoneNum)) {
            verifyCodeRequest.email = phoneNum;
        } else if (ValueUtil.isChinaPhoneLegal(phoneNum)) {
            verifyCodeRequest.phoneNum = phoneNum;
        }
        verifyCodeRequest.type = Constant.VerifyCode_RETPWD;
        mPresenter.onRequestVerifyCode(verifyCodeRequest);
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void getVerifyCodeSuccess(String code) {
        LogUtils.e("code:" + code);
        if (!TextUtils.isEmpty(code)) {
            if (ValueUtil.isValidityEmail(mForgetPwdPhoneEt.getText().toString())) {
                showToast("获取验证码成功，请去邮箱查看");
            } else {
                mForgetPwdVerifyEt.setText(code);
            }
        } else {
            showToast("操作过于频繁，请稍后再试");
        }
    }

    /**
     * 请求 -忘记密码
     */
    private void onRequestForgetPwd() {
        RegisterRequest forgetPwdRequest = new RegisterRequest();
        forgetPwdRequest.account = mForgetPwdPhoneEt.getText().toString();
        forgetPwdRequest.code = mForgetPwdVerifyEt.getText().toString();
        forgetPwdRequest.pwd = mForgetPwdEt.getText().toString();
        forgetPwdRequest.clientType = Constant.FROM;
        mPresenter.onRequestForgetPwd(forgetPwdRequest);
    }

    /**
     * 注册成功
     */
    @Override
    public void getRegisterSuccess() {
    }

    /**
     * 忘记密码修改成功
     */
    @Override
    public void getForgetPwdSuccess() {
        showToast("修改成功");
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
        DaggerRegisterComponent.builder().appComponent(getAppComponent())
                .registerModule(new RegisterModule(ForgetPwdActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
