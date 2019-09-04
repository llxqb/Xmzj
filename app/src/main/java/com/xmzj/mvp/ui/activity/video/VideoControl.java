package com.xmzj.mvp.ui.activity.video;


import com.xmzj.entity.response.VideoClassifyResponse;
import com.xmzj.entity.response.VideoInfoResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class VideoControl {
    public interface VideoView extends LoadDataView {

        void getVideoClassifySuccess(VideoClassifyResponse videoClassifyResponse);

        void getVideoInfoSuccess(VideoInfoResponse videoInfoResponse);

        void getVideoCollectionSuccess();
    }

    public interface PresenterVideo extends Presenter<VideoView> {

        /**
         * 视频分类
         */
        void onRequestVideoClassify();

        /**
         * 视频详情
         */
        void onRequestVideoInfo(String videoId);

        /**
         * 视频收藏
         * episodeId: 视频id
         */
        void onRequestVideoCollection(String episodeId);
    }

}
