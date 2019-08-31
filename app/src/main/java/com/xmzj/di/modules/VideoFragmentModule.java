package com.xmzj.di.modules;


import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;
import com.xmzj.mvp.ui.activity.video.VideoFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class VideoFragmentModule {

    private VideoFragmentControl.VideoFragmentView mVideoFragmentView;

    public VideoFragmentModule(LoadDataView view) {
        if (view instanceof HomeFragmentControl.HomeView) {
            mVideoFragmentView = (VideoFragmentControl.VideoFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    VideoFragmentControl.VideoFragmentView videoFragmentView() {
        return this.mVideoFragmentView;
    }


}
