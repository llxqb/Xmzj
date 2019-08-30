package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.LoginModel;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.ui.activity.register.RegisterControl;
import com.xmzj.mvp.ui.activity.register.RegisterPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.LoginApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class RegisterModule {
    private final AppCompatActivity activity;
    private RegisterControl.RegisterView view;

    public RegisterModule(AppCompatActivity activity, RegisterControl.RegisterView view) {
        this.activity = activity;
        this.view = view;
    }

    public RegisterModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    RegisterControl.RegisterView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    RegisterControl.PresenterRegister providePresenterRegister(RegisterPresenterImpl registerPresenter) {
        return registerPresenter;
    }

    @Provides
    @PerActivity
    LoginModel provideMainModel(Gson gson, ModelTransform modelTransform) {
        return new LoginModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(LoginApi.class), gson, modelTransform);
    }


}
