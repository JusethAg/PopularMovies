package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by JusethAg on 9/5/2016.
 */
public class MainInteractorImplTest extends BaseTest{

    @Mock
    private MainRepository mainRepository;

    private MainInteractorImpl mainInteractor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainInteractor = new MainInteractorImpl(mainRepository);
    }

    @Test
    public void testExecRestartListMovies_CallResetValues() throws Exception {
        mainInteractor.execRestartListMovies();
        verify(mainRepository).resetValues();
    }

    @Test
    public void testExecGetPopularListMovies_CallGetPopularMoviesAndIncreaseMoviePage()
            throws Exception {
        mainInteractor.execGetPopularListMovies();
        verify(mainRepository).getPopularMovies();
        verify(mainRepository).increaseMoviePage();
    }

    @Test
    public void testExecGetTopRatedListMovies_CallGetTopRatedMoviesAndIncreaseMoviePage()
            throws Exception {
        mainInteractor.execGetTopRatedListMovies();
        verify(mainRepository).getTopRatedMovies();
        verify(mainRepository).increaseMoviePage();
    }
}