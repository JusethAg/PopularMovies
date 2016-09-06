package com.jusethag.popularmovies.main;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;

    public MainInteractorImpl(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void execRestartListMovies() {
        mainRepository.resetValues();
    }

    @Override
    public void execGetPopularListMovies() {
        mainRepository.getPopularMovies();
        mainRepository.increaseMoviePage();
    }

    @Override
    public void execGetTopRatedListMovies() {
        mainRepository.getTopRatedMovies();
        mainRepository.increaseMoviePage();
    }

}
