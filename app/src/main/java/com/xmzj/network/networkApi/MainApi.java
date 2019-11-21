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



}
