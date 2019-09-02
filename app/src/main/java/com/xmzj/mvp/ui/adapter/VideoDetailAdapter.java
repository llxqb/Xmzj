package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.help.ImageLoaderHelper;
import com.xmzj.mvp.utils.DateUtil;

import java.util.List;


/**
 * 音频列表adapter
 */
public class VideoDetailAdapter extends BaseQuickAdapter<VideoInfoResponse.EpisodesBean, BaseViewHolder> {

    private Context mContext;
    private ImageLoaderHelper mImageLoaderHelper;

    public VideoDetailAdapter(Context context, @Nullable List<VideoInfoResponse.EpisodesBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_detail_video, data);
        mContext = context;
        mImageLoaderHelper = imageLoaderHelper;
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoInfoResponse.EpisodesBean item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.item_video_detail_layout);
        ImageView coverIv = helper.getView(R.id.cover_iv);
        helper.setText(R.id.title, item.getTitle());
        mImageLoaderHelper.displayImage(mContext, item.getCover(), coverIv, R.mipmap.video1);
        helper.setText(R.id.author, item.getInfo()).setText(R.id.date, DateUtil.getStrTime(item.getCreateTime(), DateUtil.TIME_YYMMDD));
    }

}
