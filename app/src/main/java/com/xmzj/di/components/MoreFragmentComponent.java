package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.MainModule;
import com.xmzj.di.modules.MineFragmentModule;
import com.xmzj.di.modules.MoreFragmentModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.fragment.MineFragment;
import com.xmzj.mvp.ui.fragment.MoreFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, MoreFragmentModule.class})
public interface MoreFragmentComponent {
    AppCompatActivity activity();

    void inject(MoreFragment fragment);
}
