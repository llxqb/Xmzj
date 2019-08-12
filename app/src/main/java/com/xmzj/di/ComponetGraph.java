package com.xmzj.di;


import com.xmzj.XmzjApp;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.mvp.ui.activity.splash.SplashActivity;

/**
 * Created by wxl on 16/3/30.
 *
 */
public interface ComponetGraph {

    void inject(XmzjApp application);

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

    void inject(SplashActivity splashActivity);
//    void inject(EarnBeansActivity earnBeansActivity);
//    void inject(MatchSuccessDialog matchSuccessDialog);
//    void inject(CustomerService service);

}
