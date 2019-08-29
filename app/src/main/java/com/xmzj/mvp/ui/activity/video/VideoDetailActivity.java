package com.xmzj.mvp.ui.activity.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            url = videoResponse.url;
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

    String url;
    String mVideoPath;

    @OnClick({R.id.common_back, R.id.common_iv_right, R.id.collection_tv, R.id.download_tv, R.id.share_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_iv_right:
                break;
            case R.id.collection_tv:
                break;
            case R.id.download_tv:
                downloadVideo(); //处理具体下载过程
                break;
            case R.id.share_tv:
                break;
        }
    }


    /**
     * 下载视频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(url, new DownloadListener() {
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
                        showToast("下载成功");
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
                        mCircleProgressLayout.setVisibility(View.GONE);
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
                        Toast.makeText(VideoDetailActivity.this, erroInfo, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void startBtnCLick() {
        String localFilePath = DownloadUtil.checkFileIsExist(url);
        LogUtils.e("localFilePath:" + localFilePath);
        if (!TextUtils.isEmpty(localFilePath)) {
            //本地有资源
            showToast("播放本地视频");
            mMyJzvdStd.setUp(localFilePath, videoResponse.title, Jzvd.SCREEN_NORMAL);
        } else {
            mMyJzvdStd.setUp(url, videoResponse.title);
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
