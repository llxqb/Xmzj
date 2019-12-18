package com.xmzj.mvp.ui.activity.main;


import com.xmzj.entity.response.VersionUpdateResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class MineFragmentControl {
    public interface MineView extends LoadDataView {
        void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse);
    }

    public interface mineFragmentPresenter extends Presenter<MineView> {
        /**
         * 检查版本更新
         */
        void onRequestVersionUpdate();
    }


}
