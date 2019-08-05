package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.MainModel;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;
import com.xmzj.mvp.ui.activity.main.HomeFragmentPresenterImpl;
import com.xmzj.mvp.ui.activity.main.MainControl;
import com.xmzj.mvp.ui.activity.main.MainPresenterImpl;
import com.xmzj.mvp.ui.activity.main.MineFragmentControl;
import com.xmzj.mvp.ui.activity.main.MineFragmentPresenterImpl;
import com.xmzj.mvp.ui.activity.main.MoreFragmentControl;
import com.xmzj.mvp.ui.activity.main.MoreFragmentPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.MainApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MainModule {
    private final AppCompatActivity activity;
    private MainControl.MainView view;

    public MainModule(AppCompatActivity activity, MainControl.MainView view) {
        this.activity = activity;
        this.view = view;
    }

    public MainModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    MainControl.MainView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    MainControl.PresenterMain providePresenterMain(MainPresenterImpl presenterMain) {
        return presenterMain;
    }

    @Provides
    @PerActivity
    MainModel provideMainModel(Gson gson, ModelTransform modelTransform) {
        return new MainModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.KENCANME_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(MainApi.class), gson, modelTransform);
    }


    //add
    @Provides
    @PerActivity
   HomeFragmentControl.homeFragmentPresenter providePresenterHomeFragment(HomeFragmentPresenterImpl homeFragmentPresenter) {
        return homeFragmentPresenter;
    }

    @Provides
    @PerActivity
    MineFragmentControl.mineFragmentPresenter providePresenterMineFragment(MineFragmentPresenterImpl mineFragmentPresenter) {
        return mineFragmentPresenter;
    }

    @Provides
    @PerActivity
    MoreFragmentControl.moreFragmentPresenter providePresenterMoreFragment(MoreFragmentPresenterImpl moreFragmentPresenter) {
        return moreFragmentPresenter;
    }


}
