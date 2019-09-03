package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.mvp.utils.DateUtil;

import java.util.List;


/**
 * 音频下载历史列表adapter
 */
public class AudioDownLoadHistoryAdapter extends BaseQuickAdapter<AudioListResponse.DataBean, BaseViewHolder> {

    private Context mContext;

    public AudioDownLoadHistoryAdapter(Context context, @Nullable List<AudioListResponse.DataBean> data) {
        super(R.layout.item_audio, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, AudioListResponse.DataBean item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.upload_iv).addOnClickListener(R.id.share_iv).addOnClickListener(R.id.audio_item_layout);
        helper.setText(R.id.audio_title, item.getTitle())
                .setText(R.id.play_time, String.valueOf(item.getBrowseNum()))
//                .setText(R.id.look_time, String.valueOf(item.lookNum))
                .setText(R.id.upload_time, DateUtil.getStrTime(item.getCreateTime(), DateUtil.TIME_YYMMDD));
    }

}
