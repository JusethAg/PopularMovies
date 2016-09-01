package com.jusethag.popularmovies;

import android.app.Application;

import com.jusethag.popularmovies.libs.di.LibsModule;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class PopularMoviesApp extends Application {
    private LibsModule libsModule;

    @Override
    public void onCreate() {
        super.onCreate();

        initModules();
    }

    private void initModules() {
        libsModule = new LibsModule();
    }
}
