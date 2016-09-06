package com.jusethag.popularmovies.main.ui;

import com.jusethag.popularmovies.entities.Movie;

import java.util.List;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface MainView {
    void showProgress();
    void hideProgress();

    void resetMovies(List<Movie> movies);
    void setMovies(List<Movie> movies);

    void onGetMoviesError(String error);
}
