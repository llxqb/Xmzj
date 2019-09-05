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
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.utils.ValueUtil;
import com.xmzj.mvp.views.TimeButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterControl.RegisterView {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.register_user_et)
    EditText mRegisterUserEt;
    @BindView(R.id.register_phone_et)
    EditText mRegisterPhoneEt;
    @BindView(R.id.register_pwd_et)
    EditText mRegisterPwdEt;
    @BindView(R.id.register_verify_et)
    EditText mRegisterVerifyEt;
    @BindView(R.id.code_bt)
    TimeButton mCodeBt;
    @BindView(R.id.register_btn)
    TextView mRegisterBtn;

    @Inject
    RegisterControl.PresenterRegister mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_register);
        setStatusBar();
        initInjectData();
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText("注册");
//        mRegisterPhoneEt.addTextChangedListener(search_text_OnChange);
    }

    @Override
    protected void initData() {
//        mRegisterPhoneEt.setText("960555267@qq.com");
    }


    @OnClick({R.id.common_back, R.id.code_bt, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.code_bt:
                if (verification()) {
                    if (TextUtils.isEmpty(mRegisterPhoneEt.getText().toString())) {
                        showToast("手机号/邮箱不能为空");
                        return;
                    }
                    mCodeBt.setRun(true);
                    mCodeBt.setAfterBg(R.drawable.verify_text_background);
                    onRequestVerifyCode(mRegisterPhoneEt.getText().toString(), Constant.VerifyCode_REGISTER);
                }
                break;
            case R.id.register_btn:
                if (verification()) {
                    if (TextUtils.isEmpty(mRegisterPhoneEt.getText().toString())) {
                        showToast("手机号/邮箱不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(mRegisterVerifyEt.getText().toString())) {
                        showToast("验证码不能为空");
                        return;
                    }
                    onRequestRegister();
                }
                break;
        }
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
        if (!TextUtils.isEmpty(code)) {
            if (ValueUtil.isValidityEmail(mRegisterPhoneEt.getText().toString())) {
                showToast("获取验证码成功，请去邮箱查看");
            } else {
                mRegisterVerifyEt.setText(code);
            }
        } else {
            showToast("操作过于频繁，请稍后再试");
        }
    }

    /**
     * 注册
     */
    private void onRequestRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.account = mRegisterUserEt.getText().toString();
        registerRequest.pwd = mRegisterPwdEt.getText().toString();
        registerRequest.code = mRegisterVerifyEt.getText().toString();
        registerRequest.clientType = Constant.FROM;
        mPresenter.onRequestRegister(registerRequest);
//        showLoading("注册成功");
//        finish();
    }

    /**
     * 验证
     */
    private boolean verification() {
        if (TextUtils.isEmpty(mRegisterUserEt.getText().toString())) {
            showToast("账号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mRegisterPwdEt.getText().toString())) {
            showToast("密码不能为空");
            return false;
        }
        if (!ValueUtil.pwdCheckSpecialString(mRegisterPwdEt.getText().toString())) {
            showToast("密码格式不正确");
            return false;
        }
        return true;
    }

    /**
     * 注册成功
     */
    @Override
    public void getRegisterSuccess() {
        showToast("注册成功");
        finish();
    }

    /**
     * 忘记密码修改成功
     */
    @Override
    public void getForgetPwdSuccess() {
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
                .registerModule(new RegisterModule(RegisterActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
