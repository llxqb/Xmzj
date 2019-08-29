package com.xmzj;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.di.components.AppComponent;
import com.xmzj.di.components.DaggerAppComponent;
import com.xmzj.di.modules.AppModule;

import javax.inject.Inject;



/**
 * Created by li.liu on 18/1/8.
 * Application
 */
public class XmzjApp extends Application {

    private AppComponent mAppComponent;
    public Context mContext;
    @Inject
    Gson mGson;
//    @Inject
//    BuProcessor mBuProcessor;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);//必须有
    }

}
