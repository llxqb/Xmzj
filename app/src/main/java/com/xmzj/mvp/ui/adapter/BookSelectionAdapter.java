package com.xmzj.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.ChapterListResponse;

import java.util.List;


/**
 * 书籍章节/选集 adapter
 */
public class BookSelectionAdapter extends BaseQuickAdapter<ChapterListResponse.DataBean, BaseViewHolder> {

    public BookSelectionAdapter(@Nullable List<ChapterListResponse.DataBean> data) {
        super(R.layout.item_book_selection, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ChapterListResponse.DataBean item) {
        helper.addOnClickListener(R.id.item_book_selection_layout);
        if (TextUtils.isEmpty(item.getSectionName())) {
            helper.setText(R.id.chapter_name_tv, "章节" + item.getId());
        } else {
            helper.setText(R.id.chapter_name_tv, item.getSectionName());
        }
    }
}
