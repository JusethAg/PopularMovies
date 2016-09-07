package com.jusethag.popularmovies;

import android.app.Application;

import com.jusethag.popularmovies.detail.ui.DetailActivity;
import com.jusethag.popularmovies.detail.di.DaggerDetailComponent;
import com.jusethag.popularmovies.detail.di.DetailComponent;
import com.jusethag.popularmovies.libs.di.LibsModule;

import com.jusethag.popularmovies.main.di.DaggerMainComponent;
import com.jusethag.popularmovies.main.di.MainComponent;
import com.jusethag.popularmovies.main.di.MainModule;
import com.jusethag.popularmovies.main.ui.MainActivity;
import com.jusethag.popularmovies.main.ui.MainView;
import com.jusethag.popularmovies.main.ui.adapters.OnItemClickListener;
import com.jusethag.popularmovies.main.ui.adapters.OnRecyclerScrollListener;

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

    public MainComponent getMainComponent(MainActivity activity, MainView mainView,
                                          OnItemClickListener onItemClickListener,
                                          OnRecyclerScrollListener onRecyclerScrollListener) {
        libsModule.setActivity(activity);

        return DaggerMainComponent.builder()
                .libsModule(libsModule)
                .mainModule(new MainModule(mainView, onItemClickListener, onRecyclerScrollListener))
                .build();
    }

    public DetailComponent getDetailComponent(DetailActivity detailActivity) {
        libsModule.setActivity(detailActivity);

        return DaggerDetailComponent.builder()
                .libsModule(libsModule)
                .build();
    }
}
