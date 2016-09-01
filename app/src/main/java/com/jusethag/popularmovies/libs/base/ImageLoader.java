package com.jusethag.popularmovies.libs.base;


import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface ImageLoader {
    void load(ImageView imageView, String url);
}
