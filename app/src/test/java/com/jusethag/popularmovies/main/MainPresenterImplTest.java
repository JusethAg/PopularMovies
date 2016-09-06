package com.jusethag.popularmovies.main;

import com.jusethag.popularmovies.BaseTest;
import com.jusethag.popularmovies.entities.Movie;
import com.jusethag.popularmovies.libs.base.EventBus;
import com.jusethag.popularmovies.main.events.MovieListEvent;
import com.jusethag.popularmovies.main.ui.MainView;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by JusethAg on 9/5/2016.
 */
public class MainPresenterImplTest extends BaseTest {

    @Mock
    private MovieListEvent movieListEvent;
    @Mock
    private List<Movie> movies;
    @Mock
    private EventBus eventBus;
    @Mock
    private MainView mainView;
    @Mock
    private MainInteractor mainInteractor;

    private MainPresenterImpl mainPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainPresenter = new MainPresenterImpl(eventBus, mainView, mainInteractor);
    }

    @Test
    public void testOnCreate_SubscribedToEventBus() throws Exception {
        mainPresenter.onCreate();
        verify(eventBus).register(mainPresenter);
    }

    @Test
    public void testOnDestroy_UnsubscribedToEventBus() throws Exception {
        mainPresenter.onDestroy();
        verify(eventBus).unregister(mainPresenter);
    }

    @Test
    public void testRestartList_ExecuteMainInteractor() throws Exception {
        mainPresenter.restartList();
        verify(mainInteractor).execRestartListMovies();
    }

    @Test
    public void testGetPopularMovies_ExecuteMainInteractor() throws Exception {
        mainPresenter.getPopularMovies();
        verify(mainInteractor).execGetPopularListMovies();
    }

    @Test
    public void testGetTopRatedMovies_ExecuteMainInteractor() throws Exception {
        mainPresenter.getTopRatedMovies();
        verify(mainInteractor).execGetTopRatedListMovies();
    }

    @Test
    public void testOnResetValuesEvent_ResetValuesOnView() throws Exception {
        when(movieListEvent.getError()).thenReturn(null);
        when(movieListEvent.getType()).thenReturn(MovieListEvent.RESET_VALUES_EVENT);
        when(movieListEvent.getMovies()).thenReturn(movies);

        mainPresenter.onEventMainThread(movieListEvent);
        verify(mainView).resetMovies(movies);
    }

    @Test
    public void testOnGetPopularEvent_SetMoviesOnView() throws Exception {
        when(movieListEvent.getError()).thenReturn(null);
        when(movieListEvent.getType()).thenReturn(MovieListEvent.GET_POPULAR_EVENT);
        when(movieListEvent.getMovies()).thenReturn(movies);

        mainPresenter.onEventMainThread(movieListEvent);
        verify(mainView).setMovies(movies);
    }

    @Test
    public void testOnGetTopRatedEvent_SetMoviesOnView() throws Exception {
        when(movieListEvent.getError()).thenReturn(null);
        when(movieListEvent.getType()).thenReturn(MovieListEvent.GET_TOP_RATED_EVENT);
        when(movieListEvent.getMovies()).thenReturn(movies);

        mainPresenter.onEventMainThread(movieListEvent);
        verify(mainView).setMovies(movies);
    }

    @Test
    public void testOnEventMainThread_ShowMovieError() throws Exception {
        when(movieListEvent.getError()).thenReturn(new String());

        mainPresenter.onEventMainThread(movieListEvent);
        verify(mainView).onGetMoviesError(new String());
    }
}
