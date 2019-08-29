package com.xmzj.di.modules;


import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.ui.activity.main.HomeFragmentControl;
import com.xmzj.mvp.ui.activity.main.MoreFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MoreFragmentModule {

    private MoreFragmentControl.MoreFragmentView mMoreFragmentView;

    public MoreFragmentModule(LoadDataView view) {
        if (view instanceof HomeFragmentControl.HomeView) {
            mMoreFragmentView = (MoreFragmentControl.MoreFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    MoreFragmentControl.MoreFragmentView MoreFragmentView() {
        return this.mMoreFragmentView;
    }


}
