package com.xmzj.mvp.ui.activity.audio;


import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class AudioFragmentControl {
    public interface AudioFragmentView extends LoadDataView {
        void getAudioListSuccess(AudioListResponse audioListResponse);
    }

    public interface AudioFragmentPresenter extends Presenter<AudioFragmentView> {
        /**
         * 请求音频列表
         */
        void onRequestAudioList(VideoListRequest videoRequest);
    }


}
