package com.jusethag.popularmovies.detail.di;

import com.jusethag.popularmovies.detail.ui.DetailActivity;
import com.jusethag.popularmovies.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JusethAg on 9/6/2016.
 */
@Singleton
@Component(modules = {LibsModule.class})
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
