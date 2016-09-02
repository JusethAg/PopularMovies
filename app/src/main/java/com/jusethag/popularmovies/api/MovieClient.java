package com.jusethag.popularmovies.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MovieClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private Retrofit retrofit;

    public MovieClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        return retrofit.create(MovieService.class);
    }
}
