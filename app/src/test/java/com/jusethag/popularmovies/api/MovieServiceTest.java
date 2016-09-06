package com.jusethag.popularmovies.api;

import com.jusethag.popularmovies.BaseTest;
import com.jusethag.popularmovies.BuildConfig;
import com.jusethag.popularmovies.api.models.MovieResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by JusethAg on 9/1/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MovieServiceTest extends BaseTest {
    private MovieService movieService;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        MovieClient movieClient = new MovieClient();
        movieService = movieClient.getMovieService();
    }

    @Test
    public void doGetPopularMovies_getMovieFromAPI() throws Exception {
        int page = 1;
        Call<MovieResponse> call = movieService.getPopularMovies(
                BuildConfig.THEMOVIEDB_API_KEY, page);

        Response<MovieResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        assertNotNull(response.body().getPage());
        assertNotNull(response.body().getMovies());

        MovieResponse movieResponse = response.body();
        assertEquals(page, movieResponse.getPage());

    }

    @Test
    public void doGetPopularMovies_getNoMovieFromAPI() throws Exception {
        int page = 10000;
        Call<MovieResponse> call = movieService.getPopularMovies(
                BuildConfig.THEMOVIEDB_API_KEY, page);

        Response<MovieResponse> response = call.execute();
        assertFalse(response.isSuccessful());

        assertNull(response.body());

    }


}
