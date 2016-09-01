package com.jusethag.popularmovies.libs.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JusethAg on 9/1/2016.
 */
@Singleton
@Component(modules = {LibsModule.class})
public interface LibsComponent {
}
