package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.BookModel;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.ui.activity.book.BooksControl;
import com.xmzj.mvp.ui.activity.book.BooksPresenterImpl;
import com.xmzj.mvp.ui.fragment.book.BooksChildFragmentControl;
import com.xmzj.mvp.ui.fragment.book.BooksChildFragmentPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.BookApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class BooksModule {
    private final AppCompatActivity activity;
    private BooksControl.BooksView view;

    public BooksModule(AppCompatActivity activity, BooksControl.BooksView view) {
        this.activity = activity;
        this.view = view;
    }

    public BooksModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    BooksControl.BooksView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    BooksControl.PresenterBooks providePresenterBooks(BooksPresenterImpl booksPresenter) {
        return booksPresenter;
    }

    @Provides
    @PerActivity
    BookModel provideBookModel(Gson gson, ModelTransform modelTransform) {
        return new BookModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(BookApi.class), gson, modelTransform);
    }

    //add
    @Provides
    @PerActivity
    BooksChildFragmentControl.BooksChildFragmentPresenter providePresenterBooksChildFragment(BooksChildFragmentPresenterImpl booksChildFragmentPresenter) {
        return booksChildFragmentPresenter;
    }

}
