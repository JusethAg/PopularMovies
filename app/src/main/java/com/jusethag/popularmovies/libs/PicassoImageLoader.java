package com.jusethag.popularmovies.libs;

import android.app.Activity;
import android.widget.ImageView;

import com.jusethag.popularmovies.libs.base.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class PicassoImageLoader implements ImageLoader {
    private Activity activity;

    public void setLoaderContext(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void load(ImageView imageView, String url) {
        Picasso.with(activity).load(url)
                .into(imageView);
    }
}
