package com.xmzj.mvp.views.dialog;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.entity.user.BuProcessor;
import com.xmzj.help.ImageLoaderHelper;
import com.xmzj.mvp.ui.adapter.BookSelectionAdapter;
import com.xmzj.mvp.utils.SystemUtils;


/**
 * 章节列表PopupWindow
 */
public class ChapterListPopupWindow {
    private Activity mContext;
    private ChapterListPopupWindowListener mPopupWindowListener;
    private CustomPopWindow mCustomPopWindow;
    private BuProcessor mBuProcessor;
    private ImageLoaderHelper mImageLoaderHelper;
    private ChapterListResponse mChapterListResponse;
    private BookSelectionAdapter mBookSelectionAdapter;
    private ConstraintLayout mPopupChapterListLayout;

    public ChapterListPopupWindow(Activity context, ChapterListResponse chapterListResponse, ImageLoaderHelper imageLoaderHelper, ChapterListPopupWindowListener popupWindowListener) {
        mContext = context;
        mChapterListResponse = chapterListResponse;
        mImageLoaderHelper = imageLoaderHelper;
        mPopupWindowListener = popupWindowListener;
    }

    public void setBackGroundColor(int color) {
        mPopupChapterListLayout.setBackgroundColor(color);
    }

    public void setDismiss() {
        if (mCustomPopWindow != null) {
            mCustomPopWindow.getPopupWindow().dismiss();
        }
    }

    public void initPopWindow(View view) {
        if (mCustomPopWindow == null) {
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_chapter_list, null);
            //处理popWindow 显示内容
            handlePopListView(contentView);
            //创建并显示popWindow
            mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                    .setView(contentView)
                    .enableBackgroundDark(true)
                    .size(SystemUtils.getScreenWidth(mContext) * 4 / 5, ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                    .create();
            mCustomPopWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        }
    }


    private void handlePopListView(View contentView) {
        mPopupChapterListLayout = contentView.findViewById(R.id.popup_chapter_list_layout);
        ImageView mBookCoverIv = contentView.findViewById(R.id.book_cover_iv);
        TextView mBookNameTv = contentView.findViewById(R.id.book_name_tv);
        TextView mTotalChapterNumTv = contentView.findViewById(R.id.total_chapter_num_tv);
        RecyclerView mRecyclerView = contentView.findViewById(R.id.recycler_view);
//        mImageLoaderHelper.displayRoundedCornerImage(mContext, mReadingBookResponse.getCatalogue().getSquare_cover(), mBookCoverIv, 4, Constant.LOADING_DEFAULT_2);
//        mBookNameTv.setText(mReadingBookResponse.getCatalogue().getBook_name());
//        String totalWords = "Total " + mSelectionResponse.getWords() + " bab";
//        mTotalChapterNumTv.setText(totalWords);
        mBookSelectionAdapter = new BookSelectionAdapter(mChapterListResponse.getData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mBookSelectionAdapter);
        mBookSelectionAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ChapterListResponse.DataBean dataBean = (ChapterListResponse.DataBean) adapter.getItem(position);
            if (mPopupWindowListener != null) {
                if (dataBean != null) {
                    mPopupWindowListener.switchChapterPage(dataBean.getId());
                    mCustomPopWindow.dissmiss();
                }
            }
        });

    }


    public interface ChapterListPopupWindowListener {
        void switchChapterPage(String chapterId);
    }
}
