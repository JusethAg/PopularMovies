package com.jusethag.popularmovies.main.events;

import com.jusethag.popularmovies.entities.Movie;

import java.util.List;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MovieListEvent {

    public static final int RESET_VALUES_EVENT = 0;
    public static final int GET_POPULAR_EVENT = 1;
    public static final int GET_TOP_RATED_EVENT = 2;

    private List<Movie> movies;
    private int type;
    private String error;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
