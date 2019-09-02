package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.model.VideoModel;
import com.xmzj.mvp.ui.activity.search.SearchControl;
import com.xmzj.mvp.ui.activity.search.SearchPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.AudioAndVideoApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class SearchModule {
    private final AppCompatActivity activity;
    private SearchControl.SearchView view;

    public SearchModule(AppCompatActivity activity, SearchControl.SearchView view) {
        this.activity = activity;
        this.view = view;
    }

    public SearchModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    SearchControl.SearchView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    SearchControl.PresenterSearch providePresenterSearch(SearchPresenterImpl searchPresenter) {
        return searchPresenter;
    }

    @Provides
    @PerActivity
    VideoModel provideVideoModel(Gson gson, ModelTransform modelTransform) {
        return new VideoModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(AudioAndVideoApi.class), gson, modelTransform);
    }


}
