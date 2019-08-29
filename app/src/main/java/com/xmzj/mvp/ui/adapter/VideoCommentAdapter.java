package com.xmzj.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmzj.R;
import com.xmzj.entity.response.CommentResponse;

import java.util.List;


/**
 * 音频列表adapter
 */
public class VideoCommentAdapter extends BaseQuickAdapter<CommentResponse, BaseViewHolder> {

    private Context mContext;

    public VideoCommentAdapter(Context context, @Nullable List<CommentResponse> data) {
        super(R.layout.item_video_detail_comment, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, CommentResponse item) {
        if (item == null) return;
        helper.setText(R.id.username, item.username)
                .setText(R.id.comment_content, item.commentContent)
                .setText(R.id.comment_date, item.commentDate);
    }

}
