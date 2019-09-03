package com.xmzj.mvp.ui.activity.audio;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AudioDownLoadHistoryActivity extends BaseActivity {

    @BindView(R.id.common_back)
    ImageView mCommonBack;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_audio_down_load_history);
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText("音频下载历史");
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAudioAdapter = new AudioAdapter(getActivity(), audioContentResponseList);
//        mRecyclerView.setAdapter(mAudioAdapter);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }
}
