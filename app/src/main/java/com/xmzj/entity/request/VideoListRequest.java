package com.xmzj.entity.request;

/**
 * 音频/视频 列表请求request
 */
public class VideoListRequest {
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
    /**
     * 页码 （必填）
     */
    public int pageNo;
    /**
     * 每页记录数 （必填）
     */
    public int pageSize;
}
