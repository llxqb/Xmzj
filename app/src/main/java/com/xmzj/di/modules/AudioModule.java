package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.model.VideoModel;
import com.xmzj.mvp.ui.activity.audio.AudioControl;
import com.xmzj.mvp.ui.activity.audio.AudioFragmentControl;
import com.xmzj.mvp.ui.activity.audio.AudioFragmentPresenterImpl;
import com.xmzj.mvp.ui.activity.audio.AudioPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.AudioAndVideoApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class AudioModule {
    private final AppCompatActivity activity;
    private AudioControl.AudioView view;

    public AudioModule(AppCompatActivity activity, AudioControl.AudioView view) {
        this.activity = activity;
        this.view = view;
    }

    public AudioModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    AudioControl.AudioView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    AudioControl.PresenterAudio providePresenterAudio(AudioPresenterImpl audioPresenter) {
        return audioPresenter;
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


    //add
    @Provides
    @PerActivity
    AudioFragmentControl.AudioFragmentPresenter providePresenterAudioFragment(AudioFragmentPresenterImpl audioFragmentPresenter) {
        return audioFragmentPresenter;
    }

//    @Provides
//    @PerActivity
//    MineFragmentControl.mineFragmentPresenter providePresenterMineFragment(MineFragmentPresenterImpl mineFragmentPresenter) {
//        return mineFragmentPresenter;
//    }
//
//    @Provides
//    @PerActivity
//    MoreFragmentControl.moreFragmentPresenter providePresenterMoreFragment(MoreFragmentPresenterImpl moreFragmentPresenter) {
//        return moreFragmentPresenter;
//    }


}
