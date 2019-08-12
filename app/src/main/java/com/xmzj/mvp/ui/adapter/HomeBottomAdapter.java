package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.HomeBottomFunctionResponse;
import com.xmzj.entity.response.HomeFunctionResponse;
import com.xmzj.help.ImageLoaderHelper;

import java.util.List;


/**
 * Mime 页面 MyAlbumAdapter
 */
public class HomeBottomAdapter extends BaseQuickAdapter<HomeBottomFunctionResponse, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;
    private Context mContext;

    public HomeBottomAdapter(Context context, @Nullable List<HomeBottomFunctionResponse> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.home_bottom_item, data);
        mContext = context;
        mImageLoaderHelper = imageLoaderHelper;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeBottomFunctionResponse item) {
        if (item == null) return;
        helper.addOnClickListener(R.id.home_bottom_item_ll);
        ImageView functionImg = helper.getView(R.id.bottom_function_img);
        mImageLoaderHelper.displayImage(mContext, item.img, functionImg);
        helper.setText(R.id.bottom_function_text,item.text);
    }

}
