package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface MainApi {
    /**
     * 用户首页信息
     */
    @GET("home")
    Observable<String> onRequestRecommendAudio();

    /**
     * 请求版本更新
     */
    @GET("app/getVersion")
    Observable<String> onRequestVersionUpdate();

    /**
     * 请求banner数据
     */
    @GET("bunner")
    Observable<String> onRequestBanner();


}
