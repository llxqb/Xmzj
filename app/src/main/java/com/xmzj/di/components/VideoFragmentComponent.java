package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.VideoFragmentModule;
import com.xmzj.di.modules.VideoModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.fragment.VideoFragmentFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {VideoModule.class, VideoFragmentModule.class})
public interface VideoFragmentComponent {
    AppCompatActivity activity();

    void inject(VideoFragmentFragment fragment);
}
