package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface LoginApi {
    /**
     * 登录
     */
    @GET("/auth/login")
    Observable<String> onRequestLoginInfo(@Body String request);

    /**
     * 注册
     */
    @POST("/auth/login")
    Observable<String> onRequestRegister(@Body String request);
    /**
     * 忘记密码
     */
    @POST("/auth/login")
    Observable<String> onRequestForgetPwd(@Body String request);


}
