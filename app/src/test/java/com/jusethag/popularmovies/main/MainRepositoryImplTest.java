package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.BaseTest;
import com.jusethag.popularmovies.BuildConfig;
import com.jusethag.popularmovies.api.MovieService;
import com.jusethag.popularmovies.api.models.MovieResponse;
import com.jusethag.popularmovies.entities.Movie;
import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.main.events.MovieListEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;


import java.io.IOException;
import java.util.List;
import java.util.Random;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by JusethAg on 9/5/2016.
 */
public class MainRepositoryImplTest extends BaseTest {

    @Mock
    private List<Movie> movies;
    @Mock
    private EventBus eventBus;
    @Mock
    private MovieService movieService;

    private MainRepositoryImpl mainRepository;
    private ArgumentCaptor<MovieListEvent> movieListEventArgumentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainRepository = new MainRepositoryImpl(eventBus, movieService);
        movieListEventArgumentCaptor = ArgumentCaptor.forClass(MovieListEvent.class);
    }

    @Test
    public void testResetValues_EventPosted() throws Exception {
        mainRepository.resetValues();
        verify(eventBus).post(movieListEventArgumentCaptor.capture());

        MovieListEvent movieListEvent = movieListEventArgumentCaptor.getValue();

        assertEquals(MovieListEvent.RESET_VALUES_EVENT, movieListEvent.getType());
        assertNull(movieListEvent.getError());
    }

    @Test
    public void testGetPopularMoviesSuccess_EventPosted() throws Exception {
        int page = 1;
        when(movieService.getPopularMovies(BuildConfig.THEMOVIEDB_API_KEY,
                                            page)).thenReturn(buildCall(true, null));

        mainRepository.getPopularMovies();

        verify(movieService).getPopularMovies(BuildConfig.THEMOVIEDB_API_KEY,
                                                page);
        verify(eventBus).post(movieListEventArgumentCaptor.capture());

        MovieListEvent movieListEvent = movieListEventArgumentCaptor.getValue();

        assertEquals(MovieListEvent.GET_POPULAR_EVENT, movieListEvent.getType());
        assertNull(movieListEvent.getError());
        assertNotNull(movieListEvent.getMovies());
        assertEquals(movies, movieListEvent.getMovies());
    }

    @Test
    public void testGetPopularMoviesFail_EventPosted() throws Exception {
        int page = 1;
        String errorMsg = "error";
        when(movieService.getPopularMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page)).thenReturn(buildCall(false, errorMsg));

        mainRepository.getPopularMovies();

        verify(movieService).getPopularMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page);
        verify(eventBus).post(movieListEventArgumentCaptor.capture());

        MovieListEvent movieListEvent = movieListEventArgumentCaptor.getValue();

        assertEquals(MovieListEvent.GET_POPULAR_EVENT, movieListEvent.getType());
        assertNotNull(movieListEvent.getError());
        assertEquals(errorMsg, movieListEvent.getError());
        assertNull(movieListEvent.getMovies());
    }

    @Test
    public void testGetTopRatedMoviesSuccess_EventPosted() throws Exception {
        int page = 1;
        when(movieService.getTopRatedMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page)).thenReturn(buildCall(true, null));

        mainRepository.getTopRatedMovies();

        verify(movieService).getTopRatedMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page);
        verify(eventBus).post(movieListEventArgumentCaptor.capture());

        MovieListEvent movieListEvent = movieListEventArgumentCaptor.getValue();

        assertEquals(MovieListEvent.GET_TOP_RATED_EVENT, movieListEvent.getType());
        assertNull(movieListEvent.getError());
        assertNotNull(movieListEvent.getMovies());
        assertEquals(movies, movieListEvent.getMovies());
    }

    @Test
    public void testGetTopRatedMoviesFail_EventPosted() throws Exception {
        int page = 1;
        String errorMsg = "error";
        when(movieService.getTopRatedMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page)).thenReturn(buildCall(false, errorMsg));

        mainRepository.getTopRatedMovies();

        verify(movieService).getTopRatedMovies(BuildConfig.THEMOVIEDB_API_KEY,
                page);
        verify(eventBus).post(movieListEventArgumentCaptor.capture());

        MovieListEvent movieListEvent = movieListEventArgumentCaptor.getValue();

        assertEquals(MovieListEvent.GET_TOP_RATED_EVENT, movieListEvent.getType());
        assertNotNull(movieListEvent.getError());
        assertEquals(errorMsg, movieListEvent.getError());
        assertNull(movieListEvent.getMovies());
    }

    private Call<MovieResponse> buildCall(final boolean success, final String errorMsg) {
        Call<MovieResponse> responseCall = new Call<MovieResponse>() {
            @Override
            public Response<MovieResponse> execute() throws IOException {

                if (success) {
                    MovieResponse movieResponse = new MovieResponse();
                    movieResponse.setPage(1);
                    movieResponse.setMovies(movies);
                    return Response.success(movieResponse);
                } else {
                    return Response.error(null, null);
                }
            }

            @Override
            public void enqueue(Callback<MovieResponse> callback) {
                if (success){
                    try {
                        callback.onResponse(this, execute());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callback.onFailure(this, new Throwable(errorMsg));
                }
            }

            @Override
            public boolean isExecuted() {
                return true;
            }

            @Override
            public void cancel() {
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MovieResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return responseCall;
    }

}
