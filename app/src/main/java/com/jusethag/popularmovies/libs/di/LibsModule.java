package com.jusethag.popularmovies.libs.di;

import android.app.Activity;

import com.jusethag.popularmovies.libs.GreenRobotEventBus;
import com.jusethag.popularmovies.libs.PicassoImageLoader;
import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JusethAg on 9/1/2016.
 */
@Module
public class LibsModule {
    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(Activity activity) {
        PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
        if (activity != null) {
            picassoImageLoader.setLoaderContext(activity);
        }
        return picassoImageLoader;
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return this.activity;
    }

}
