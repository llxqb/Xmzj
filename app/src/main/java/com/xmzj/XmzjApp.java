package com.xmzj;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xmzj.di.components.AppComponent;
import com.xmzj.di.components.DaggerAppComponent;
import com.xmzj.di.modules.AppModule;

import javax.inject.Inject;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;


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

        initFileDownLoad();
    }


    /**
     * 初始化HTTP请求和文件下载管理框架
     */
    private void initFileDownLoad(){
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
