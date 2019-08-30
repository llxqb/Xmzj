package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.RegisterModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.activity.register.ForgetPwdActivity;
import com.xmzj.mvp.ui.activity.register.RegisterActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {RegisterModule.class, ActivityModule.class})
public interface RegisterComponent extends ActivityComponent {
    //对LoginActivity进行依赖注入
    void inject(RegisterActivity registerActivity);
    void inject(ForgetPwdActivity forgetPwdActivity);

    AppCompatActivity activity();


}
