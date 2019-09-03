package com.xmzj.di.modules;


import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.ui.activity.audio.AudioFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class AudioFragmentModule {

    private AudioFragmentControl.AudioFragmentView mAudioFragmentView;

    public AudioFragmentModule(LoadDataView view) {
        if (view instanceof AudioFragmentControl.AudioFragmentView) {
            mAudioFragmentView = (AudioFragmentControl.AudioFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    AudioFragmentControl.AudioFragmentView audioFragmentView() {
        return this.mAudioFragmentView;
    }


}
