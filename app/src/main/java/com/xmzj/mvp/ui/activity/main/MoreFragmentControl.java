package com.xmzj.mvp.ui.activity.main;


import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class MoreFragmentControl {
    public interface HomeView extends LoadDataView {
//        void getInfoSuccess(HomeFragmentResponse response);

    }

    public interface moreFragmentPresenter extends Presenter<HomeView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
