package com.xmzj.mvp.ui.activity.book;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bifan.txtreaderlib.bean.TxtChar;
import com.bifan.txtreaderlib.bean.TxtMsg;
import com.bifan.txtreaderlib.interfaces.ICenterAreaClickListener;
import com.bifan.txtreaderlib.interfaces.ILoadListener;
import com.bifan.txtreaderlib.interfaces.ISliderListener;
import com.bifan.txtreaderlib.interfaces.ITextSelectListener;
import com.bifan.txtreaderlib.main.TxtConfig;
import com.bifan.txtreaderlib.main.TxtReaderView;
import com.xmzj.R;
import com.xmzj.di.components.DaggerReadBookComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.ReadBookModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.BookChapterContentResponse;
import com.xmzj.entity.response.ChapterListResponse;
import com.xmzj.mvp.views.dialog.ChapterListPopupWindow;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 阅读页面
 */
public class ReadBookActivity extends BaseActivity implements ReadBookControl.ReadBookView, ChapterListPopupWindow.ChapterListPopupWindowListener {
    @Inject
    ReadBookControl.PresenterReadBook mPresenter;
    @BindView(R.id.read_book_layout)
    ConstraintLayout mReadBookLayout;
    @BindView(R.id.activity_hwtxtplay_readerView)
    TxtReaderView mTxtReaderView;
    @BindView(R.id.chapter_menu_tv)
    TextView mChapterMenuText;
    @BindView(R.id.chapter_name_tv)
    TextView mChapterNameTv;
    @BindView(R.id.activity_hwtxtplay_top)
    RelativeLayout mTopDecoration;
    @BindView(R.id.progress_text_tv)
    TextView mProgressText;
    @BindView(R.id.setting_tv)
    TextView mSettingText;
    @BindView(R.id.activity_hwtxtplay_bottom)
    RelativeLayout mBottomDecoration;
    @BindView(R.id.txtreadr_menu_title)
    TextView mTitle;
    @BindView(R.id.txtreadr_menu_chapter_pre)
    TextView mChapterPre;
    @BindView(R.id.txtreadr_menu_seekbar)
    SeekBar mSeekBar;
    @BindView(R.id.txtreadr_menu_chapter_next)
    TextView mChapterNext;
    @BindView(R.id.txtreadr_menu_textsize_del)
    RelativeLayout mTextsizeDel;
    @BindView(R.id.txtreadr_menu_textsize)
    TextView mTextSize;
    @BindView(R.id.txtreadr_menu_textsize_add)
    RelativeLayout mTextSizeAdd;
    @BindView(R.id.txtreadr_menu_textsetting1_bold)
    LinearLayout mBoldSelectedLayout;
    @BindView(R.id.txtreadr_menu_textsetting1_normal)
    LinearLayout mNormalSelectedLayout;
    @BindView(R.id.txtreadr_menu_textsetting1)
    LinearLayout mTextsetting1;
    @BindView(R.id.txtreadr_menu_textsetting2_cover)
    LinearLayout mCoverSelectedLayout;
    @BindView(R.id.txtreadr_menu_textsetting2_translate)
    LinearLayout mTranslateSelectedLayout;
    @BindView(R.id.txtreadr_menu_textsetting2_shear)
    LinearLayout mShearSelectedLayout;
    @BindView(R.id.txtreadr_menu_textsetting2)
    LinearLayout mTextsetting2;
    @BindView(R.id.activity_hwtxtplay_menu_top)
    LinearLayout mTopMenu;
    @BindView(R.id.activity_hwtxtplay_menu_bottom)
    LinearLayout mBottomMenu;
    @BindView(R.id.activity_hwtxtplay_cover)
    View mCoverView;
    @BindView(R.id.activity_hwtxtplay_clipboar_layout)
    RelativeLayout ClipboardView;
    @BindView(R.id.activity_hwtxtplay_selected_text)
    TextView mSelectedText;
    @BindView(R.id.tag_1)
    TextView mTag1;
    @BindView(R.id.tag_2)
    TextView mTag2;
    @BindView(R.id.hwtxtreader_menu_style1)
    View mStyle1;
    @BindView(R.id.hwtxtreader_menu_style2)
    View mStyle2;
    @BindView(R.id.hwtxtreader_menu_style3)
    View mStyle3;
    @BindView(R.id.hwtxtreader_menu_style4)
    View mStyle4;
    @BindView(R.id.hwtxtreader_menu_style5)
    View mStyle5;
    //    @BindView(R.id.chapter_name)
//    TextView mChapterNameTv;
    @BindView(R.id.charpter_progress)
    TextView mCharpterProgress;
    private String mBookId;
    private String mChapterId;
    private String mBookName;
    private String mChapterName;

    public static void start(Context context, String bookId, String chapterId, String bookName, String chapterName) {
        Intent intent = new Intent(context, ReadBookActivity.class);
        intent.putExtra("bookId", bookId);
        intent.putExtra("chapterId", chapterId);
        intent.putExtra("bookName", bookName);
        intent.putExtra("chapterName", chapterName);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_read_book);
        initInjectData();
    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            mBookId = getIntent().getStringExtra("bookId");
            mChapterId = getIntent().getStringExtra("chapterId");
            mBookName = getIntent().getStringExtra("bookName");
            mChapterName = getIntent().getStringExtra("chapterName");
            onRequestChapterContent();
        }
    }

    @Override
    protected void initData() {
        registerListener();
    }

    @OnClick({R.id.back_iv, R.id.chapter_menu_tv, R.id.setting_tv, R.id.clipboar_click, R.id.txtreadr_menu_chapter_pre, R.id.txtreadr_menu_chapter_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.chapter_menu_tv:
                //显示章节列表
                if (mChapterListResponse == null) {
                    onRequestSelectionInfo();
                } else {
                    if (mChapterListPopupWindow == null) {
                        mChapterListPopupWindow = new ChapterListPopupWindow(this, mChapterListResponse, mImageLoaderHelper, this);
                    } else {
                        mChapterListPopupWindow.setDismiss();
                        mChapterListPopupWindow = null;
                        mChapterListPopupWindow = new ChapterListPopupWindow(this, mChapterListResponse, mImageLoaderHelper, this);
                    }
                    mChapterListPopupWindow.initPopWindow(mReadBookLayout);
                    mChapterListPopupWindow.setBackGroundColor(mTxtReaderView.getBackgroundColor());
                }
                break;
            case R.id.setting_tv:
                Show(mTopMenu, mBottomMenu, mCoverView);
                break;
            case R.id.clipboar_click://复制
                onCopyText(view);
                break;
            case R.id.txtreadr_menu_chapter_pre://上一章
//                mCatalogueId =
                onRequestChapterContent();
                break;
            case R.id.txtreadr_menu_chapter_next://下一章
                onRequestChapterContent();
                break;
        }
    }

    /**
     * 书籍章节内容
     */
    private void onRequestChapterContent() {
        mPresenter.onRequestChapterContent(mBookId, mChapterId);
    }

    @Override
    public void getChapterContentSuccess(BookChapterContentResponse bookChapterContentResponse) {
        mTxtReaderView.loadText(bookChapterContentResponse.getData().getContent(), new ILoadListener() {
            @Override
            public void onSuccess() {
                //加载成功回调
                initWhenLoadDone();
            }

            @Override
            public void onFail(TxtMsg txtMsg) {
                //加载失败回调
            }

            @Override
            public void onMessage(String message) {
                //加载过程信息回调
//                LogUtils.e("message()" + message);
            }
        });
    }


    /**
     * 请求书籍选集信息
     */
    private void onRequestSelectionInfo() {
        mPresenter.onRequestChapterList(mBookId);
    }

    private ChapterListPopupWindow mChapterListPopupWindow;
    private ChapterListResponse mChapterListResponse;

    @Override
    public void getChapterListSuccess(ChapterListResponse chapterListResponse) {
        mChapterListResponse = chapterListResponse;
        if (mChapterListPopupWindow == null) {
            mChapterListPopupWindow = new ChapterListPopupWindow(this, chapterListResponse, mImageLoaderHelper, this);
        } else {
            mChapterListPopupWindow.setDismiss();
            mChapterListPopupWindow = null;
            mChapterListPopupWindow = new ChapterListPopupWindow(this, chapterListResponse, mImageLoaderHelper, this);
        }
        mChapterListPopupWindow.initPopWindow(mReadBookLayout);
        mChapterListPopupWindow.setBackGroundColor(mTxtReaderView.getBackgroundColor());
    }


    @Override
    public void switchChapterPage(String chapterId) {
        mChapterId = chapterId;
        onRequestChapterContent();
    }

    protected void initWhenLoadDone() {
        TxtConfig.saveIsOnVerticalPageMode(this,false);
        mTitle.setText(mBookName);
        mChapterNameTv.setText(mChapterName);
        mTextSize.setText(String.valueOf(mTxtReaderView.getTextSize()));
        mTopDecoration.setBackgroundColor(mTxtReaderView.getBackgroundColor());
        mBottomDecoration.setBackgroundColor(mTxtReaderView.getBackgroundColor());
        //mTxtReaderView.setLeftSlider(new MuiLeftSlider());//修改左滑动条
        //mTxtReaderView.setRightSlider(new MuiRightSlider());//修改右滑动条
        //字体初始化
        onTextSettingUi(mTxtReaderView.getTxtReaderContext().getTxtConfig().Bold);
        //翻页初始化
        onPageSwitchSettingUi(mTxtReaderView.getTxtReaderContext().getTxtConfig().Page_Switch_Mode);
        //保存的翻页模式
        int pageSwitchMode = mTxtReaderView.getTxtReaderContext().getTxtConfig().Page_Switch_Mode;
        if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SERIAL) {
            mTxtReaderView.setPageSwitchByTranslate();
        } else if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_COVER) {
            mTxtReaderView.setPageSwitchByCover();
        } else if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SHEAR) {
            mTxtReaderView.setPageSwitchByShear();
        }
        //章节初始化
        if (mTxtReaderView.getChapters() != null && mTxtReaderView.getChapters().size() > 0) {
            WindowManager m = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            m.getDefaultDisplay().getMetrics(metrics);
        } else {
//            Gone(mChapterMenuText);
        }
    }

    private void onTextSettingUi(Boolean isBold) {
        if (isBold) {
            mBoldSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_selected);
            mNormalSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
        } else {
            mBoldSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
            mNormalSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_selected);
        }
    }

    private void onPageSwitchSettingUi(int pageSwitchMode) {
        if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SERIAL) {
            mTranslateSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_selected);
            mCoverSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
            mShearSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
        } else if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_COVER) {
            mTranslateSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
            mCoverSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_selected);
            mShearSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
        } else if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SHEAR) {
            mTranslateSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
            mCoverSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_unselected);
            mShearSelectedLayout.setBackgroundResource(com.bifan.txtreaderlib.R.drawable.shape_menu_textsetting_selected);
        }
    }

    protected void registerListener() {
        setMenuListener();
        setSeekBarListener();
        setCenterClickListener();
        setPageChangeListener();
        setOnTextSelectListener();
        setStyleChangeListener();
        setExtraListener();
    }

    protected void setMenuListener() {
        mCoverView.setOnTouchListener((view, motionEvent) -> {
            Gone(mTopMenu, mBottomMenu, mCoverView);
            return true;
        });
    }

    protected void setSeekBarListener() {
        mSeekBar.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                mTxtReaderView.loadFromProgress(mSeekBar.getProgress());
            }
            return false;
        });
    }

    protected void setCenterClickListener() {
        mTxtReaderView.setOnCenterAreaClickListener(new ICenterAreaClickListener() {
            @Override
            public boolean onCenterClick(float widthPercentInView) {
                mSettingText.performClick();
                return true;
            }

            @Override
            public boolean onOutSideCenterClick(float widthPercentInView) {
                if (mBottomMenu.getVisibility() == View.VISIBLE) {
                    mSettingText.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    protected void setPageChangeListener() {
        mTxtReaderView.setPageChangeListener(progress -> {
            int p = (int) (progress * 1000);
            mProgressText.setText(((float) p / 10) + "%");
            mSeekBar.setProgress((int) (progress * 100));
        });
    }

    protected void setOnTextSelectListener() {
        mTxtReaderView.setOnTextSelectListener(new ITextSelectListener() {
            @Override
            public void onTextChanging(TxtChar firstSelectedChar, TxtChar lastSelectedChar) {
                //firstSelectedChar.Top
                //  firstSelectedChar.Bottom
                // 这里可以根据 firstSelectedChar与lastSelectedChar的top与bottom的位置
                //计算显示你要显示的弹窗位置，如果需要的话
            }

            @Override
            public void onTextChanging(String selectText) {
                onCurrentSelectedText(selectText);
            }

            @Override
            public void onTextSelected(String selectText) {
                onCurrentSelectedText(selectText);
            }
        });

        mTxtReaderView.setOnSliderListener(new ISliderListener() {
            @Override
            public void onShowSlider(TxtChar txtChar) {
                //TxtChar 为当前长按选中的字符
                // 这里可以根据 txtChar的top与bottom的位置
                //计算显示你要显示的弹窗位置，如果需要的话
            }

            @Override
            public void onShowSlider(String currentSelectedText) {
                onCurrentSelectedText(currentSelectedText);
                Show(ClipboardView);
            }

            @Override
            public void onReleaseSlider() {
                Gone(ClipboardView);
            }
        });
    }

    protected void setStyleChangeListener() {
        mStyle1.setOnClickListener(new StyleChangeClickListener(ContextCompat.getColor(this, com.bifan.txtreaderlib.R.color.hwtxtreader_styleclor1), StyleTextColors[0]));
        mStyle2.setOnClickListener(new StyleChangeClickListener(ContextCompat.getColor(this, com.bifan.txtreaderlib.R.color.hwtxtreader_styleclor2), StyleTextColors[1]));
        mStyle3.setOnClickListener(new StyleChangeClickListener(ContextCompat.getColor(this, com.bifan.txtreaderlib.R.color.hwtxtreader_styleclor3), StyleTextColors[2]));
        mStyle4.setOnClickListener(new StyleChangeClickListener(ContextCompat.getColor(this, com.bifan.txtreaderlib.R.color.hwtxtreader_styleclor4), StyleTextColors[3]));
        mStyle5.setOnClickListener(new StyleChangeClickListener(ContextCompat.getColor(this, com.bifan.txtreaderlib.R.color.hwtxtreader_styleclor5), StyleTextColors[4]));
    }


    private class StyleChangeClickListener implements View.OnClickListener {
        private int BgColor;
        private int TextColor;

        public StyleChangeClickListener(int bgColor, int textColor) {
            BgColor = bgColor;
            TextColor = textColor;
        }

        @Override
        public void onClick(View view) {
            mTxtReaderView.setStyle(BgColor, TextColor);
            mTopDecoration.setBackgroundColor(BgColor);
            mBottomDecoration.setBackgroundColor(BgColor);
        }
    }


    public void onCopyText(View view) {
        if (!TextUtils.isEmpty(CurrentSelectedText)) {
            showToast("已经复制到粘贴板");
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(CurrentSelectedText + "");
        }
        onCurrentSelectedText("");
        mTxtReaderView.releaseSelectedState();
        Gone(ClipboardView);
    }


    private void setExtraListener() {
        mTextSizeAdd.setOnClickListener(new TextChangeClickListener(true));
        mTextsizeDel.setOnClickListener(new TextChangeClickListener(false));
        mBoldSelectedLayout.setOnClickListener(new TextSettingClickListener(true));
        mNormalSelectedLayout.setOnClickListener(new TextSettingClickListener(false));
        mTranslateSelectedLayout.setOnClickListener(new SwitchSettingClickListener(TxtConfig.PAGE_SWITCH_MODE_SERIAL));
        mCoverSelectedLayout.setOnClickListener(new SwitchSettingClickListener(TxtConfig.PAGE_SWITCH_MODE_COVER));
        mShearSelectedLayout.setOnClickListener(new SwitchSettingClickListener(TxtConfig.PAGE_SWITCH_MODE_SHEAR));
    }

    private class TextChangeClickListener implements View.OnClickListener {
        private Boolean Add;

        public TextChangeClickListener(Boolean pre) {
            Add = pre;
        }

        @Override
        public void onClick(View view) {
            int textSize = mTxtReaderView.getTextSize();
            if (Add) {
                if (textSize + 2 <= TxtConfig.MAX_TEXT_SIZE) {
                    mTxtReaderView.setTextSize(textSize + 2);
                    mTextSize.setText(textSize + 2 + "");
                }
            } else {
                if (textSize - 2 >= TxtConfig.MIN_TEXT_SIZE) {
                    mTxtReaderView.setTextSize(textSize - 2);
                    mTextSize.setText(textSize - 2 + "");
                }
            }
        }
    }


    private class TextSettingClickListener implements View.OnClickListener {
        private Boolean Bold;

        public TextSettingClickListener(Boolean bold) {
            Bold = bold;
        }

        @Override
        public void onClick(View view) {
            mTxtReaderView.setTextBold(Bold);
            onTextSettingUi(Bold);
        }
    }

    private class SwitchSettingClickListener implements View.OnClickListener {
        private int pageSwitchMode;

        public SwitchSettingClickListener(int pageSwitchMode) {
            this.pageSwitchMode = pageSwitchMode;
        }

        @Override
        public void onClick(View view) {
            if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_COVER) {
                mTxtReaderView.setPageSwitchByCover();
            } else if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SERIAL) {
                mTxtReaderView.setPageSwitchByTranslate();
            }
            if (pageSwitchMode == TxtConfig.PAGE_SWITCH_MODE_SHEAR) {
                mTxtReaderView.setPageSwitchByShear();
            }
            onPageSwitchSettingUi(pageSwitchMode);
        }
    }


    private final int[] StyleTextColors = new int[]{
            Color.parseColor("#4a453a"),
            Color.parseColor("#505550"),
            Color.parseColor("#453e33"),
            Color.parseColor("#8f8e88"),
            Color.parseColor("#27576c")
    };


    protected void Gone(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    protected void Show(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    protected String CurrentSelectedText;

    private void onCurrentSelectedText(String SelectedText) {
        mSelectedText.setText("选中" + (SelectedText + "").length() + "个文字");
        CurrentSelectedText = SelectedText;
    }

    private void initInjectData() {
        DaggerReadBookComponent.builder().appComponent(getAppComponent())
                .readBookModule(new ReadBookModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
