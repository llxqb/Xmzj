package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.HomeFragmentModule;
import com.xmzj.di.modules.MainModule;
import com.xmzj.di.modules.MineFragmentModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.fragment.HomeFragment;
import com.xmzj.mvp.ui.fragment.MineFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, MineFragmentModule.class})
public interface MineFragmentComponent {
    AppCompatActivity activity();

    void inject(MineFragment fragment);
}
