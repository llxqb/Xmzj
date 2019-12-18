package com.xmzj.mvp.ui.activity.main;


import com.xmzj.entity.response.BannerResponse;
import com.xmzj.entity.response.HomeRecommendAudioResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class HomeFragmentControl {
    public interface HomeView extends LoadDataView {
        void getRecommendAudioSuccess(HomeRecommendAudioResponse recommendAudioResponse);

        void getBannerSuccess(BannerResponse bannerResponse);

    }

    public interface homeFragmentPresenter extends Presenter<HomeView> {
        /**
         * 请求homeFragment 推荐音频列表
         */
        void onRequestRecommendAudio();

        /**
         * 获取banner数据
         */
        void onRequestBanner();

    }


}
