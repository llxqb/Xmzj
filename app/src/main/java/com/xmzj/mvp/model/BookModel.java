package com.xmzj.mvp.model;


import com.google.gson.Gson;
import com.xmzj.entity.request.BooksRequest;
import com.xmzj.network.networkApi.BookApi;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class BookModel {
    private final BookApi mBookApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public BookModel(BookApi api, Gson gson, ModelTransform transform) {
        mBookApi = api;
        mGson = gson;
        mTransform = transform;
    }


    /**
     * 请求书库分类
     */
    public Observable<ResponseData> onRequestBookType() {
        return mBookApi.onRequestBookType().map(mTransform::transformListType);
    }

    /**
     * 书库列表
     */
    public Observable<ResponseData> onRequestBookList(BooksRequest request) {
        return mBookApi.onRequestBookList(request.categoryId, request.keyword, request.orderCol, request.pageNo, request.pageSize).map(mTransform::transformListType);
    }

    /**
     * 书籍章节列表
     */
    public Observable<ResponseData> onRequestChapterList(String bookId) {
        return mBookApi.onRequestChapterList(bookId).map(mTransform::transformListType);
    }

    /**
     * 书籍章节内容
     */
    public Observable<ResponseData> onRequestChapterContent(String bookId, String sectionId) {
        return mBookApi.onRequestChapterContent(bookId, sectionId).map(mTransform::transformListType);
    }

}
