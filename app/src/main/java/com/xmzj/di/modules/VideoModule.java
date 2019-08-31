package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.model.VideoModel;
import com.xmzj.mvp.ui.activity.video.VideoControl;
import com.xmzj.mvp.ui.activity.video.VideoFragmentControl;
import com.xmzj.mvp.ui.activity.video.VideoFragmentPresenterImpl;
import com.xmzj.mvp.ui.activity.video.VideoPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.AudioAndVideoApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class VideoModule {
    private final AppCompatActivity activity;
    private VideoControl.VideoView view;

    public VideoModule(AppCompatActivity activity, VideoControl.VideoView view) {
        this.activity = activity;
        this.view = view;
    }

    public VideoModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    VideoControl.VideoView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    VideoControl.PresenterVideo providePresenterVideo(VideoPresenterImpl videoPresenter) {
        return videoPresenter;
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
   VideoFragmentControl.VideoFragmentPresenter providePresenterVideoFragment(VideoFragmentPresenterImpl videoFragmentPresenter) {
        return videoFragmentPresenter;
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
