package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.main.events.MovieListEvent;
import com.jusethag.popularmovies.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MainPresenterImpl implements MainPresenter {

    private EventBus eventBus;
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(EventBus eventBus, MainView mainView, MainInteractor mainInteractor) {
        this.eventBus = eventBus;
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        this.mainView = null;
    }

    @Override
    public void restartList() {
        mainInteractor.execRestartListMovies();
    }

    @Override
    public void getPopularMovies() {
        if (this.mainView != null) {
            mainView.showProgress();
        }
        mainInteractor.execGetPopularListMovies();
    }

    @Override
    public void getTopRatedMovies() {
        if (this.mainView != null) {
            mainView.showProgress();
        }
        mainInteractor.execGetTopRatedListMovies();
    }

    @Subscribe
    @Override
    public void onEventMainThread(MovieListEvent movieListEvent) {
        if (this.mainView != null) {
            String error = movieListEvent.getError();
            if (error == null) {
                switch (movieListEvent.getType()) {
                    case MovieListEvent.RESET_VALUES_EVENT:
                        mainView.resetMovies(movieListEvent.getMovies());
                        break;
                    case MovieListEvent.GET_POPULAR_EVENT:
                        mainView.hideProgress();
                        mainView.setMovies(movieListEvent.getMovies());
                        break;

                    case MovieListEvent.GET_TOP_RATED_EVENT:
                        mainView.hideProgress();
                        mainView.setMovies(movieListEvent.getMovies());
                        break;
                }
            } else {
                mainView.hideProgress();
                mainView.onGetMoviesError(error);
            }
        }
    }
}
