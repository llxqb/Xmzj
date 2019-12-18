package com.xmzj.mvp.ui.activity.main;


import com.xmzj.entity.response.VersionUpdateResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class MainControl {
    public interface MainView extends LoadDataView {

        void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse);
    }

    public interface PresenterMain extends Presenter<MainView> {

        /**
         * 检查版本更新
         */
        void onRequestVersionUpdate();
    }

}
