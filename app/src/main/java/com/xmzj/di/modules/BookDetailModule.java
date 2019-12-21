package com.xmzj.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xmzj.BuildConfig;
import com.xmzj.di.scopes.PerActivity;
import com.xmzj.mvp.model.BookModel;
import com.xmzj.mvp.model.ModelTransform;
import com.xmzj.mvp.ui.activity.book.BookDetailControl;
import com.xmzj.mvp.ui.activity.book.BookDetailPresenterImpl;
import com.xmzj.network.RetrofitUtil;
import com.xmzj.network.networkApi.BookApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class BookDetailModule {
    private final AppCompatActivity activity;
    private BookDetailControl.BookDetailView view;

    public BookDetailModule(AppCompatActivity activity, BookDetailControl.BookDetailView view) {
        this.activity = activity;
        this.view = view;
    }

    public BookDetailModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    BookDetailControl.BookDetailView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    BookDetailControl.PresenterBookDetail providePresenterBookDetail(BookDetailPresenterImpl bookDetailPresenter) {
        return bookDetailPresenter;
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


}
