package com.xmzj.di.components;


import android.support.v7.app.AppCompatActivity;

import com.xmzj.di.modules.BookFragmentModule;
import com.xmzj.di.modules.BooksModule;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.ui.fragment.book.BooksChildFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {BooksModule.class, BookFragmentModule.class})
public interface BookFragmentComponent {
    AppCompatActivity activity();

    void inject(BooksChildFragment fragment);
}
