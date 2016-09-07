package com.jusethag.popularmovies.main.di;

import com.jusethag.popularmovies.api.MovieClient;
import com.jusethag.popularmovies.api.MovieService;
import com.jusethag.popularmovies.models.Movie;
import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.libs.base.ImageLoader;
import com.jusethag.popularmovies.main.MainInteractor;
import com.jusethag.popularmovies.main.MainInteractorImpl;
import com.jusethag.popularmovies.main.MainPresenter;
import com.jusethag.popularmovies.main.MainPresenterImpl;
import com.jusethag.popularmovies.main.MainRepository;
import com.jusethag.popularmovies.main.MainRepositoryImpl;
import com.jusethag.popularmovies.main.ui.MainView;
import com.jusethag.popularmovies.main.ui.adapters.MovieAdapter;
import com.jusethag.popularmovies.main.ui.adapters.OnItemClickListener;
import com.jusethag.popularmovies.main.ui.adapters.OnRecyclerScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JusethAg on 9/2/2016.
 */
@Module
public class MainModule {
    private MainView mainView;
    private OnItemClickListener onItemClickListener;
    private OnRecyclerScrollListener onRecyclerScrollListener;

    public MainModule(MainView mainView, OnItemClickListener onItemClickListener,
                      OnRecyclerScrollListener onRecyclerScrollListener) {
        this.mainView = mainView;
        this.onItemClickListener = onItemClickListener;
        this.onRecyclerScrollListener = onRecyclerScrollListener;
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return this.mainView;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(EventBus eventBus, MainView mainView, MainInteractor mainInteractor) {
        return new MainPresenterImpl(eventBus, mainView, mainInteractor);
    }

    @Provides
    @Singleton
    MainInteractor providesMainInteractor(MainRepository mainRepository) {
        return new MainInteractorImpl(mainRepository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, MovieService movieService) {
        return new MainRepositoryImpl(eventBus, movieService);
    }

    @Provides
    @Singleton
    MovieService providesMovieService() {
        MovieClient movieClient = new MovieClient();
        return movieClient.getMovieService();
    }

    @Provides
    @Singleton
    MovieAdapter providesMovieAdapter(List<Movie> movies, ImageLoader imageLoader,
                                      OnItemClickListener onItemClickListener,
                                      OnRecyclerScrollListener onRecyclerScrollListener) {
        return new MovieAdapter(movies, imageLoader, onItemClickListener, onRecyclerScrollListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    OnRecyclerScrollListener providesOnRecyclerScrollListener() {
        return this.onRecyclerScrollListener;
    }

    @Provides
    @Singleton
    List<Movie> providesMovieList() {
        return new ArrayList<Movie>();
    }
}
