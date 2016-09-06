package com.jusethag.popularmovies.main.di;

import com.jusethag.popularmovies.libs.di.LibsModule;
import com.jusethag.popularmovies.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JusethAg on 9/2/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
