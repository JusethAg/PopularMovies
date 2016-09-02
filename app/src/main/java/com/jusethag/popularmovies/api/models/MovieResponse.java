package com.jusethag.popularmovies.api.models;

import com.jusethag.popularmovies.entities.Movie;

import java.util.List;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MovieResponse {
    private int page;
    private List<Movie> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
