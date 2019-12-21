package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface LoginApi {
    /**
     * 账号密码登录
     */
    @POST("auth/login")
    Observable<String> onRequestLoginInfo(@Query("account") String account, @Query("pwd") String pwd, @Query("clientType") int clientType);

    /**
     * 验证码登录
     */
    @POST("auth/codeLogin")
    Observable<String> onRequestLoginByCode(@Query("phoneNum") String phoneNum, @Query("email") String email, @Query("code") String code, @Query("clientType") int clientType);

    /**
     * 注册
     */
    @POST("auth/reg")
    Observable<String> onRequestRegister(@Query("account") String account, @Query("phoneNum") String phoneNum, @Query("email") String email, @Query("pwd") String pwd, @Query("code") String code, @Query("clientType") int clientType);

    /**
     * 忘记密码
     */
    @POST("auth/resetPwd")
    Observable<String> onRequestForgetPwd(@Query("phoneNum") String phoneNum, @Query("email") String email, @Query("pwd") String pwd, @Query("code") String code, @Query("clientType") int clientType);

    /**
     * 获取验证码
     */
    @GET("verifyCode/sendCode")
    Observable<String> onRequestVerifyCode(@Query("phoneNum") String phoneNum, @Query("email") String email, @Query("type") int type);

    /**
     * 请求个人信息
     */
    @GET("member/my")
    Observable<String> onRequestPersonalInfo(@Query("token") String token);

    /**
     * 上传图片
     */
    @POST("member/updateAvatar")
    Observable<String> uploadImageRequest(@Query("avatarFile") String avatarFile);
}
