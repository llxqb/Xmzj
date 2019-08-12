package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.HomeFunctionResponse;
import com.xmzj.help.ImageLoaderHelper;
import com.xmzj.mvp.utils.TranTools;

import java.util.List;


/**
 * Mime 页面 MyAlbumAdapter
 */
public class HomeTopAdapter extends BaseQuickAdapter<HomeFunctionResponse, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;
    private Context mContext;

    public HomeTopAdapter(Context context, @Nullable List<HomeFunctionResponse> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.home_top_item, data);
        mContext = context;
        mImageLoaderHelper = imageLoaderHelper;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeFunctionResponse item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.home_top_item_ll);
        ImageView functionImg = helper.getView(R.id.function_img);
        mImageLoaderHelper.displayImage(mContext, item.img, functionImg);
        helper.setText(R.id.function_text,item.text);
    }

}
