package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.AudioContentResponse;

import java.util.List;


/**
 * 音频列表adapter
 */
public class AudioAdapter extends BaseQuickAdapter<AudioContentResponse, BaseViewHolder> {

    private Context mContext;

    public AudioAdapter(Context context, @Nullable List<AudioContentResponse> data) {
        super(R.layout.item_audio, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, AudioContentResponse item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.upload_iv).addOnClickListener(R.id.share_iv).addOnClickListener(R.id.audio_item_layout);
        helper.setText(R.id.audio_title, item.title)
                .setText(R.id.play_time, String.valueOf(item.playNum))
                .setText(R.id.look_time, String.valueOf(item.lookNum))
                .setText(R.id.upload_time, item.uploadDate);
    }

}
