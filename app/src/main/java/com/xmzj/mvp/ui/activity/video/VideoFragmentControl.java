package com.xmzj.mvp.ui.activity.video;


import com.xmzj.entity.request.VideoListRequest;
import com.xmzj.entity.response.VideoListResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class VideoFragmentControl {
    public interface VideoFragmentView extends LoadDataView {
        void getVideoListSuccess(VideoListResponse response);

    }

    public interface VideoFragmentPresenter extends Presenter<VideoFragmentView> {
        /**
         * 请求视频列表
         */
        void onRequestVideoList(VideoListRequest videoRequest);

    }


}
