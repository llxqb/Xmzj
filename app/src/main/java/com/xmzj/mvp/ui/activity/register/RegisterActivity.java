package com.xmzj.mvp.ui.activity.register;

import android.text.TextUtils;
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
import com.xmzj.entity.response.ForgetPwdResponse;
import com.xmzj.entity.response.RegisterResponse;
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
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.common_back, R.id.code_bt, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.code_bt:
                if (verification()) {
                    mCodeBt.setRun(true);
                }
                break;
            case R.id.register_btn:
                if (verification()) {
                    if (TextUtils.isEmpty(mRegisterVerifyEt.getText().toString())) {
                        showToast("验证码不能为空");
                        return ;
                    }
                    onRequestRegister();
                }
                break;
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
        return true;
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

    private void initInjectData() {
        DaggerRegisterComponent.builder().appComponent(getAppComponent())
                .registerModule(new RegisterModule(RegisterActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
