package com.jusethag.popularmovies.libs;

import android.app.Activity;
import android.widget.ImageView;

import com.jusethag.popularmovies.libs.base.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class PicassoImageLoader implements ImageLoader {
    private Picasso picasso;

    public void setLoaderContext(Activity activity) {
        picasso.with(activity);
    }

    @Override
    public void load(ImageView imageView, String url) {
        picasso.load(url)
                .centerCrop()
                .into(imageView);
    }
}
