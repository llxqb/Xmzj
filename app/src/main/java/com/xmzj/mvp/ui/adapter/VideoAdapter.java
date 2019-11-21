package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.help.ImageLoaderHelper;

import java.util.List;


/**
 * 视频列表adapter
 */
public class VideoAdapter extends BaseQuickAdapter<VideoListResponse.DataBean, BaseViewHolder> {

    private Context mContext;
    private ImageLoaderHelper mImageLoaderHelper;

    public VideoAdapter(Context context, @Nullable List<VideoListResponse.DataBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_video, data);
        mContext = context;
        mImageLoaderHelper = imageLoaderHelper;
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoListResponse.DataBean item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.item_video_layout);
        ImageView coverIv = helper.getView(R.id.cover_iv);
        helper.setText(R.id.title, item.getTitle());
        mImageLoaderHelper.displayImage(mContext, item.getCover(), coverIv,R.mipmap.video1);
    }

}
