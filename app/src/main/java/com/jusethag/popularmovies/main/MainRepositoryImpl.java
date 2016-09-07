package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.BuildConfig;
import com.jusethag.popularmovies.api.MovieService;
import com.jusethag.popularmovies.api.models.MovieResponse;
import com.jusethag.popularmovies.models.Movie;
import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.main.events.MovieListEvent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MainRepositoryImpl implements MainRepository {

    private EventBus eventBus;
    private MovieService movieService;
    private int page = 1;

    public MainRepositoryImpl(EventBus eventBus, MovieService movieService) {
        this.eventBus = eventBus;
        this.movieService = movieService;
    }

    @Override
    public void resetValues() {
        this.page = 1;
        post(new ArrayList<Movie>(), MovieListEvent.RESET_VALUES_EVENT);
    }

    @Override
    public void getPopularMovies() {
        Call<MovieResponse> call = movieService.getPopularMovies(BuildConfig.THEMOVIEDB_API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    post(movieResponse.getMovies(), MovieListEvent.GET_POPULAR_EVENT);
                } else {
                    post(response.message(), MovieListEvent.GET_POPULAR_EVENT);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                post(t.getLocalizedMessage(), MovieListEvent.GET_POPULAR_EVENT);
            }
        });
    }

    @Override
    public void getTopRatedMovies() {
        Call<MovieResponse> call = movieService.getTopRatedMovies(BuildConfig.THEMOVIEDB_API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    post(movieResponse.getMovies(), MovieListEvent.GET_TOP_RATED_EVENT);
                } else {
                    post(response.message(), MovieListEvent.GET_TOP_RATED_EVENT);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                post(t.getLocalizedMessage(), MovieListEvent.GET_TOP_RATED_EVENT);
            }
        });
    }

    @Override
    public void increaseMoviePage() {
        if (this.page <= MainRepository.LIMIT_PAGE ) {
            this.page += 1;
        }
    }

    private void post(String error, int type) {
        MovieListEvent event = new MovieListEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }

    private void post(List<Movie> movie, int type) {
        MovieListEvent event = new MovieListEvent();
        event.setType(type);
        event.setMovies(movie);
        eventBus.post(event);
    }
}
