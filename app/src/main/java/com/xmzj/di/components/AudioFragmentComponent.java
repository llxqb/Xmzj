package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.AudioFragmentModule;
import com.xmzj.di.modules.AudioModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.fragment.AudioFragmentFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {AudioModule.class, AudioFragmentModule.class})
public interface AudioFragmentComponent {
    AppCompatActivity activity();

    void inject(AudioFragmentFragment fragment);
}
