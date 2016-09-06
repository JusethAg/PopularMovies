package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.main.events.MovieListEvent;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void restartList();
    void getPopularMovies();
    void getTopRatedMovies();
    void onEventMainThread(MovieListEvent movieListEvent);
}
