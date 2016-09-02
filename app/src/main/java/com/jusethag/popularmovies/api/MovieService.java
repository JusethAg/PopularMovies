package com.jusethag.popularmovies.api;

import com.jusethag.popularmovies.api.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface MovieService {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
        @Query("api_key") String apiKey,
        @Query("page") int page );

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page );
}
