package com.xmzj.mvp.ui.activity.audio;


import com.xmzj.entity.response.AudioClassifyResponse;
import com.xmzj.entity.response.AudioDetailInfoResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class AudioControl {
    public interface AudioView extends LoadDataView {
        void getAudioClassifySuccess(AudioClassifyResponse audioClassifyResponse);
        void getAudioDetailInfoSuccess(AudioDetailInfoResponse audioDetailInfoResponse);
        void getAudioConnectionSuccess();
    }

    public interface PresenterAudio extends Presenter<AudioView> {

        /**
         * 音频分类
         */
        void onRequestAudioClassify();

        /**
         * 音频详情
         */
        void onRequestAudioDetailInfo(String id);

        /**
         * 音频收藏
         */
        void onRequestAudioConnection(String audioId);

    }

}
