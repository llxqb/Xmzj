package com.xmzj.mvp.ui.activity.video;


import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class VideoFragmentControl {
    public interface VideoFragmentView extends LoadDataView {
//        void getInfoSuccess(HomeFragmentResponse response);

    }

    public interface VideoFragmentPresenter extends Presenter<VideoFragmentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
