package com.jusethag.popularmovies.main;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface MainRepository {
    void getPopularMovies();
    void getTopRatedMovies();
    void setMoviePage(int moviePage);
}
