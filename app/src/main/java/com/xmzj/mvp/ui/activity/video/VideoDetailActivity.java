package com.xmzj.mvp.ui.activity.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.response.CommentResponse;
import com.xmzj.entity.response.VideoResponse;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.ui.adapter.VideoCommentAdapter;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;
import com.xmzj.mvp.views.KbWithWordsCircleProgressBar;
import com.xmzj.mvp.views.MyJzvdStd;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoDetailActivity extends BaseActivity implements MyJzvdStd.MyJzStdListener {
    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_iv_right)
    ImageView mCommonIvRight;
    @BindView(R.id.myJzvdStd)
    MyJzvdStd mMyJzvdStd;
    @BindView(R.id.collection_tv)
    TextView mCollectionTv;
    @BindView(R.id.download_tv)
    TextView mDownloadTv;
    @BindView(R.id.share_tv)
    TextView mShareTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fl_circle_progress)
    FrameLayout mCircleProgressLayout;
    @BindView(R.id.circle_progress)
    KbWithWordsCircleProgressBar mCircleProgress;
    /**
     * 视频url路径
     */
    String urlPath;
    /**
     * 下载到本地视频路径
     */
    String mVideoPath;

    private VideoResponse videoResponse;
    private List<CommentResponse> commentResponseList = new ArrayList<>();

    public static void start(Context context, VideoResponse videoResponse) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("videoResponse", videoResponse);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_video_detail);
    }

    @Override
    protected void initView() {
        mMyJzvdStd.setListener(this);
        if (getIntent() != null) {
            videoResponse = getIntent().getParcelableExtra("videoResponse");
            mCommonTitleTv.setText(videoResponse.title);
            Glide.with(this).load(videoResponse.coverPic).into(mMyJzvdStd.thumbImageView);
            urlPath = videoResponse.url;
            String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
            if (!TextUtils.isEmpty(localFilePath)) {
                setDownLoadColor();
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VideoCommentAdapter mVideoCommentAdapter = new VideoCommentAdapter(this, commentResponseList);
        mRecyclerView.setAdapter(mVideoCommentAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 5; i++) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.username = "小明";
            commentResponse.commentContent = "学习了，老师辛苦了";
            commentResponse.commentDate = "2019-08-28";
            commentResponseList.add(commentResponse);
        }
    }



    @OnClick({R.id.common_back, R.id.common_iv_right, R.id.collection_tv, R.id.download_tv, R.id.share_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_iv_right:
                break;
            case R.id.collection_tv:
                setCollectionColor();
                break;
            case R.id.download_tv:
                if (TextUtils.isEmpty(DownloadUtil.checkFileIsExist(urlPath))) {
                    downloadVideo(); //处理具体下载过程
                } else {
                    showToast("已下载");
                }
                break;
            case R.id.share_tv:
                break;
        }
    }

    private void setCollectionColor() {
        mCollectionTv.setTextColor(getResources().getColor(R.color.app_color));
        //获取更换的图片
        Drawable drawable = getResources().getDrawable(R.mipmap.detail_collectioned);
        //setBounds(x,y,width,height)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mCollectionTv.setCompoundDrawables(null, null, null, drawable);
    }


    private void setDownLoadColor() {
        mDownloadTv.setTextColor(getResources().getColor(R.color.app_color));
        //获取更换的图片
        Drawable drawable = getResources().getDrawable(R.mipmap.detail_downloaded);
        //setBounds(x,y,width,height)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //mDownLoad是控件的名称,setCompoundDrawables(left,top,right,bottom)
        mDownloadTv.setCompoundDrawables(null, null, null, drawable);
    }


    /**
     * 下载视频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
            @Override
            public void onStart() {
                LogUtils.e("onStart: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载中...");
                        mCircleProgressLayout.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCircleProgress.setProgress(currentLength);
                    }
                });
            }

            @Override
            public void onFinish(String localPath) {
                mVideoPath = localPath;
                LogUtils.e("onFinish: " + localPath);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载完成");
                        mCircleProgressLayout.setVisibility(View.GONE);
                        setDownLoadColor();
                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCircleProgressLayout.setVisibility(View.GONE);
                        showToast(erroInfo);
                    }
                });
            }
        });
    }

    @Override
    public void startBtnCLick() {
        String localFilePath = DownloadUtil.checkFileIsExist(urlPath);
        LogUtils.e("localFilePath:" + localFilePath);
        if (!TextUtils.isEmpty(localFilePath)) {
            //本地有资源
            showToast("播放本地视频");
            mMyJzvdStd.setUp(localFilePath, videoResponse.title, Jzvd.SCREEN_NORMAL);
        } else {
            mMyJzvdStd.setUp(urlPath, videoResponse.title);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.goOnPlayOnPause();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }


}
