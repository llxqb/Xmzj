package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.HomeRecommendAudioResponse;

import java.util.List;


/**
 * 首页推荐音频列表adapter
 */
public class HomeRecommendAudioAdapter extends BaseQuickAdapter<HomeRecommendAudioResponse.AudiosBean, BaseViewHolder> {

    private Context mContext;

    public HomeRecommendAudioAdapter(Context context, @Nullable List<HomeRecommendAudioResponse.AudiosBean> data) {
        super(R.layout.item_home_audio, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendAudioResponse.AudiosBean item) {
        helper.addOnClickListener(R.id.upload_iv).addOnClickListener(R.id.item_home_audio);
        helper.setText(R.id.name_tv,item.getTitle());
    }

}
