package com.xmzj.di.modules;


import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.ui.fragment.book.BooksChildFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class BookFragmentModule {

    private BooksChildFragmentControl.BooksChildFragmentView mBooksChildFragmentView;

    public BookFragmentModule(LoadDataView view) {
        if (view instanceof BooksChildFragmentControl.BooksChildFragmentView) {
            mBooksChildFragmentView = (BooksChildFragmentControl.BooksChildFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    BooksChildFragmentControl.BooksChildFragmentView BooksChildFragmentView() {
        return this.mBooksChildFragmentView;
    }


}
