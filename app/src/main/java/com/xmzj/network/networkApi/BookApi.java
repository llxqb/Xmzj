package com.xmzj.network.networkApi;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by li.liu on 2019/08/29.
 * 书籍api
 */

public interface BookApi {
    /**
     * 查询书籍类型
     */
    @POST("book/store")
    Observable<String> onRequestBookType();

    /**
     * 查询书籍列表
     */
    @POST("book/list")
    Observable<String> onRequestBookList(@Query("categoryId") String categoryId, @Query("keyword") String keyword, @Query("orderCol") String orderCol, @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);
    /**
     * 书籍章节列表
     */
    @POST("book/sectionList")
    Observable<String> onRequestChapterList(@Query("bookId") String bookId);


}
