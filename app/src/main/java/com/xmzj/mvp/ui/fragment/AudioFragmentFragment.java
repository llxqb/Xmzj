package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.AudioContentResponse;
import com.xmzj.listener.DownloadListener;
import com.xmzj.mvp.ui.activity.audio.AudioPlayDetailActivity;
import com.xmzj.mvp.ui.adapter.AudioAdapter;
import com.xmzj.mvp.utils.DownloadUtil;
import com.xmzj.mvp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * AudioFragmentFragment
 */

public class AudioFragmentFragment extends BaseFragment {

    @BindView(R.id.upload_all_tv)
    TextView mUploadAllTv;
    @BindView(R.id.hot_tv)
    TextView mHotTv;
    @BindView(R.id.newest_tv)
    TextView mNewestTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<AudioContentResponse> audioContentResponseList = new ArrayList<>();
    private AudioAdapter mAudioAdapter;
    /**
     * 音频一级标题筛选
     */
    private int mType;
    /**
     * 音频二级标题筛选
     */
    private int mType2;
    /**
     * 视频url路径
     */
    String urlPath;
    /**
     * 下载到本地视频路径
     */
    String mVideoPath;

    public static AudioFragmentFragment getInstance(int type, int type2) {
        AudioFragmentFragment fragment = new AudioFragmentFragment();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        bd.putInt("type2", type2);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt("type");
            mType2 = getArguments().getInt("type2");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_audio, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAudioAdapter = new AudioAdapter(getActivity(), audioContentResponseList);
        mRecyclerView.setAdapter(mAudioAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AudioContentResponse audioContentResponse = (AudioContentResponse) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.audio_item_layout:
                        assert audioContentResponse != null;
                        AudioPlayDetailActivity.start(getActivity(), audioContentResponse);
                        break;
                    case R.id.upload_iv:
                        assert audioContentResponse != null;
                        urlPath = audioContentResponse.url;
                        if (!TextUtils.isEmpty(urlPath)) {
                            if (TextUtils.isEmpty(DownloadUtil.checkFileIsExist(urlPath))) {
                                downloadVideo(); //处理具体下载过程
                            } else {
                                showToast("已下载");
                            }
                        } else {
                            showToast("资源不存在");
                        }
                        break;
                    case R.id.share_iv:
                        showToast("分享...");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        switch (mType) {
            case 0:
                if (mType2 == 0) {
                    for (int i = 0; i < 5; i++) {
                        AudioContentResponse audioContentResponse = new AudioContentResponse();
                        audioContentResponse.title = "明心见性";
                        audioContentResponse.playNum = 500;
                        audioContentResponse.lookNum = 3000;
                        audioContentResponse.playDuration = "80分钟";
                        audioContentResponse.uploadDate = "2019-08-10";
                        audioContentResponse.url = "https://www.xinmizj.com/res/audio/src/dsbushiczsh.mp3";
                        audioContentResponseList.add(audioContentResponse);
                    }
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }


    @OnClick({R.id.upload_all_tv, R.id.hot_tv, R.id.newest_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_all_tv:
                break;
            case R.id.hot_tv:
                break;
            case R.id.newest_tv:
                break;
        }
    }

    /**
     * 下载音频文件
     */
    private void downloadVideo() {
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
            @Override
            public void onStart() {
                LogUtils.e("onStart: ");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载中...");
                    }
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onFinish(String localPath) {
                mVideoPath = localPath;
                LogUtils.e("onFinish: " + localPath);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("下载完成");
                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                LogUtils.e("onFailure: " + erroInfo);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(erroInfo);
                    }
                });
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
