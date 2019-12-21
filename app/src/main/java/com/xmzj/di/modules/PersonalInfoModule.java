package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.LoginModel;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.ui.activity.user.PersonalInfoControl;
import com.xmzj.mvp.ui.activity.user.PersonalInfoPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.LoginApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class PersonalInfoModule {
    private final AppCompatActivity activity;
    private PersonalInfoControl.PersonalInfoView view;

    public PersonalInfoModule(AppCompatActivity activity, PersonalInfoControl.PersonalInfoView view) {
        this.activity = activity;
        this.view = view;
    }

    public PersonalInfoModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    PersonalInfoControl.PersonalInfoView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    PersonalInfoControl.PresenterPersonalInfo providePresenterPersonalInfo(PersonalInfoPresenterImpl personalInfoPresenter) {
        return personalInfoPresenter;
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
