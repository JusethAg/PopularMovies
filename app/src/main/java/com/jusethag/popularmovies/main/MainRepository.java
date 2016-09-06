package com.jusethag.popularmovies.main;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface MainRepository {
    public static final int LIMIT_PAGE = 1000;

    void resetValues();
    void getPopularMovies();
    void getTopRatedMovies();
    void increaseMoviePage();

}
