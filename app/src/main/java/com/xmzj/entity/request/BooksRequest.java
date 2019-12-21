package com.xmzj.entity.request;

public class BooksRequest {
    /**
     * 分类id
     */
    public String categoryId;
    /**
     * 排序列：1：热门，2：最新
     */
    public String orderCol;
    /**
     * 搜索关键字
     */
    public String keyword;
    public String pageNo;
    public String pageSize;
}
