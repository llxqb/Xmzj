package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by li.liu on 2019/08/29.
 * 视频和音频api
 */

public interface AudioAndVideoApi {
    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    /**
     * 请求视频分类
     */
    @GET("video/category")
    Observable<String> onRequestVideoClassify();
    /**
     * 请求音频分类
     */
    @GET("audio/category")
    Observable<String> onRequestAudioClassify();

    /**
     * 请求视频列表
     */
    @GET("video/list")
    Observable<String> onRequestVideoList(@Query("categoryId") String categoryId, @Query("orderCol") String orderCol, @Query("keyword") String keyword, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);
    /**
     * 请求音频列表
     */
    @GET("audio/list")
    Observable<String> onRequestAudioList(@Query("categoryId") String categoryId, @Query("orderCol") String orderCol, @Query("keyword") String keyword, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 请求视频详情
     */
    @GET("video/{id}")
    Observable<String> onRequestVideoInfo(@Path("id") String videoId);

    /**
     * 请求视频收藏
     */
    @POST("video/episode/collect")
    Observable<String> onRequestVideoCollection(@Query("episodeId") String episodeId);


}
