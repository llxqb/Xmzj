package com.xmzj.di.components;

import android.app.Activity;


import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
