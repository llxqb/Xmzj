package com.xmzj.mvp.ui.activity.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.xmzj.R;
import com.xmzj.di.components.DaggerMainComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.MainModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.VersionUpdateResponse;
import com.xmzj.mvp.ui.activity.login.LoginActivity;
import com.xmzj.mvp.ui.adapter.MyFragmentAdapter;
import com.xmzj.mvp.ui.fragment.HomeFragment;
import com.xmzj.mvp.ui.fragment.MineFragment;
import com.xmzj.mvp.utils.UpdateManager;
import com.xmzj.mvp.views.MyNoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainControl.MainView {

    @Inject
    MainControl.PresenterMain mPresenter;
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mMainBottomNavigation;
    @BindView(R.id.main_viewpager)
    MyNoScrollViewPager mMainViewpager;
    public static final int SWITCH_HOME_PAGE = 0;
    public static final int SWITCH_MESSAGE_PAGE = 1;
    public static final int SWITCH_MINE_PAGE = 2;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
        initInjectData();
    }

    @Override
    public void initView() {
        mMainBottomNavigation.setItemIconTintList(null);
        if (!mBuProcessor.isValidLogin()) {
            startActivitys(LoginActivity.class);
            finish();
        } else {
            Log.e("ddd", "loginUser:" + new Gson().toJson(mBuProcessor.getLoginUser()));
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(new HomeFragment());
            fragments.add(new MineFragment());
//        fragments.add(moreFragment);
            MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
            mMainViewpager.setOffscreenPageLimit(fragments.size());
            mMainViewpager.setAdapter(adapter);
            mMainBottomNavigation.setOnNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void initData() {
        onRequestVersionUpdate();
    }

    /**
     * 检查版本更新
     */
    private void onRequestVersionUpdate() {
        mPresenter.onRequestVersionUpdate();
    }

    @Override
    public void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse) {
//        LogUtils.e("versionUpdateResponse:" + new Gson().toJson(versionUpdateResponse));
        if (versionUpdateResponse != null) {
            UpdateManager mUpdateManager = new UpdateManager(this, versionUpdateResponse.getData());
            mUpdateManager.checkUpdateInfo();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("exitLogin", false)) {
            //退出登录
            startActivitys(LoginActivity.class);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        resetToDefaultIcon();//重置到默认不选中图片
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                //在这里替换图标
                menuItem.setIcon(R.mipmap.main_home_click);
                mMainViewpager.setCurrentItem(SWITCH_HOME_PAGE, false);
                break;
            case R.id.action_message:
                menuItem.setIcon(R.mipmap.main_mine_click);
                mMainViewpager.setCurrentItem(SWITCH_MESSAGE_PAGE, false);
                break;
//            case R.id.action_mine:
//                menuItem.setIcon(R.mipmap.main_more_click);
//                mMainViewpager.setCurrentItem(SWITCH_MINE_PAGE, false);
//                break;
        }
        return true;
    }

    private void resetToDefaultIcon() {
        MenuItem home = mMainBottomNavigation.getMenu().findItem(R.id.action_home);
        MenuItem mine = mMainBottomNavigation.getMenu().findItem(R.id.action_message);
//        MenuItem more = mMainBottomNavigation.getMenu().findItem(R.id.action_mine);
        home.setIcon(R.mipmap.main_home);
        mine.setIcon(R.mipmap.main_mine);
//        more.setIcon(R.mipmap.main_more);
    }


    /**
     * 捕捉返回事件按钮
     * <p>
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private long exitTime = 0;

    /**
     * 退出程序
     */
    private void exitApp() {
        // 判断2次点击事件时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast(getResources().getString(R.string.exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    private void initInjectData() {
        DaggerMainComponent.builder().appComponent(getAppComponent())
                .mainModule(new MainModule(MainActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
