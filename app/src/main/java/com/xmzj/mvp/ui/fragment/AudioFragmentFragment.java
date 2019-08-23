package com.xmzj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xmzj.R;
import com.xmzj.entity.base.BaseFragment;
import com.xmzj.entity.response.AudioContentResponse;
import com.xmzj.mvp.ui.activity.audio.AudioPlayDetailActivity;
import com.xmzj.mvp.ui.adapter.AudioAdapter;

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
    private List<AudioContentResponse> audioContentResponses = new ArrayList<>();
    private AudioAdapter mAudioAdapter;
    /**
     * 音频一级标题筛选
     */
    private int mType;
    /**
     * 音频二级标题筛选
     */
    private int mType2;

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
        mAudioAdapter = new AudioAdapter(getActivity(), audioContentResponses);
        mRecyclerView.setAdapter(mAudioAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AudioContentResponse audioContentResponse = (AudioContentResponse) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.audio_item_layout:
                        assert audioContentResponse != null;
                        AudioPlayDetailActivity.start(getActivity(),audioContentResponse);
                        break;
                    case R.id.upload_iv:
                        showToast("下载...");
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
                        audioContentResponse.url = "https://v.qq.com/txp/iframe/player.html?vid=m0524y9cv04";
                        audioContentResponses.add(audioContentResponse);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
