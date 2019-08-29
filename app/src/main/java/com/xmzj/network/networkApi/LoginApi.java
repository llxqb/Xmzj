package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface LoginApi {
    /**
     * 登录
     */
    @POST("menggoda/index/user_exposure")
    Observable<String> onRequestLoginInfo(@Body String request);


}
