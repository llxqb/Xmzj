package com.xmzj.mvp.ui.activity.search;


import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.AudioListResponse;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class SearchControl {
    public interface SearchView extends LoadDataView {
        void getVideoListSuccess(VideoListResponse response);
        void getAudioListSuccess(AudioListResponse response);
    }

    public interface PresenterSearch extends Presenter<SearchView> {
        /**
         * 请求视频列表
         */
        void onRequestVideoList(VideoListRequest videoRequest);
        /**
         * 请求音频列表
         */
        void onRequestAudioList(VideoListRequest videoRequest);
    }

}
