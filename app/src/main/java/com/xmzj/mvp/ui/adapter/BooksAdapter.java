package com.xmzj.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.BooksListResponse;
import com.xmzj.help.ImageLoaderHelper;

import java.util.List;


/**
 * 书籍列表adapter
 */
public class BooksAdapter extends BaseQuickAdapter<BooksListResponse.DataBean, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;

    public BooksAdapter(@Nullable List<BooksListResponse.DataBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_books, data);
        mImageLoaderHelper = imageLoaderHelper;
    }


    @Override
    protected void convert(BaseViewHolder helper, BooksListResponse.DataBean item) {
        helper.addOnClickListener(R.id.item_book_layout);
        ImageView coverIv = helper.getView(R.id.cover_iv);
        mImageLoaderHelper.displayRoundedCornerImage(mContext, item.getCover(), coverIv, 4, R.mipmap.default_graph_2);
        helper.setText(R.id.book_name_tv, item.getName());
    }
}
