package com.xmzj.mvp.ui.activity.audio;


import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class AudioFragmentControl {
    public interface AudioFragmentView extends LoadDataView {
//        void getInfoSuccess(HomeFragmentResponse response);

    }

    public interface AudioFragmentPresenter extends Presenter<AudioFragmentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
