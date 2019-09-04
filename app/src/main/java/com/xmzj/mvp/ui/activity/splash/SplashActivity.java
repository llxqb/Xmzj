package com.xmzj.mvp.ui.activity.splash;

import android.view.WindowManager;

import com.xmzj.R;
import com.xmzj.XmzjApp;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.user.BuProcessor;
import com.xmzj.mvp.ui.activity.login.LoginActivity;
import com.xmzj.mvp.ui.activity.main.MainActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private ScheduledExecutorService scheduledThreadPool;
    @Inject
    protected BuProcessor mBuProcessor;

    @Override
    protected void initContentView() {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ((XmzjApp) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (scheduledThreadPool == null) {
            scheduledThreadPool = Executors.newScheduledThreadPool(1);
        }
        scheduledThreadPool.schedule(() -> {
            if (!mBuProcessor.isValidLogin()) {
                startActivitys(LoginActivity.class);
                finish();
            } else {
                startActivitys(MainActivity.class);
                finish();
            }
        }, 600, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (scheduledThreadPool != null) {
                scheduledThreadPool.shutdownNow();
                scheduledThreadPool = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
