package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.LoginModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.activity.login.LoginActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {LoginModule.class, ActivityModule.class})
public interface LoginComponent extends ActivityComponent {
    //对LoginActivity进行依赖注入
    void inject(LoginActivity loginActivity);

    AppCompatActivity activity();


}
