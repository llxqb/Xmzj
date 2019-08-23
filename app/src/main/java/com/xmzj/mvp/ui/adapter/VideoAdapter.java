package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.VideoResponse;

import java.util.List;


/**
 * 音频列表adapter
 */
public class VideoAdapter extends BaseQuickAdapter<VideoResponse, BaseViewHolder> {

    private Context mContext;

    public VideoAdapter(Context context, @Nullable List<VideoResponse> data) {
        super(R.layout.item_video, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoResponse item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.item_video_layout);
        ImageView coverIv = helper.getView(R.id.cover_iv);
        helper.setText(R.id.title, item.title);
        Glide.with(mContext).load(item.coverPic).into(coverIv);
    }

}
