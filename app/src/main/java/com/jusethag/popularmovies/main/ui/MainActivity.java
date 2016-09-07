package com.jusethag.popularmovies.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.jusethag.popularmovies.PopularMoviesApp;
import com.jusethag.popularmovies.R;
import com.jusethag.popularmovies.detail.ui.DetailActivity;
import com.jusethag.popularmovies.models.Movie;
import com.jusethag.popularmovies.main.MainPresenter;
import com.jusethag.popularmovies.main.ui.adapters.MovieAdapter;
import com.jusethag.popularmovies.main.ui.adapters.OnItemClickListener;
import com.jusethag.popularmovies.main.ui.adapters.OnRecyclerScrollListener;
import com.jusethag.popularmovies.settings.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener,
        OnRecyclerScrollListener {

    @BindView(R.id.container_activity_main)
    CoordinatorLayout containerMainActivity;
    @BindView(R.id.toolbarMain)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewMovies)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MovieAdapter movieAdapter;
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupInjection();
        setupToolbar();
        setupRecyclerView();

        mainPresenter.onCreate();

        getMoviesBySortOption();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.restartList();
        getMoviesBySortOption();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void setupInjection() {
        PopularMoviesApp app = (PopularMoviesApp) getApplication();
        app.getMainComponent(this, this, this, this).inject(this);
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void resetMovies(List<Movie> movies) {
        movieAdapter.setMovies(movies);
    }

    @Override
    public void setMovies(List<Movie> movies) {
        movieAdapter.updateMovies(movies);
    }

    @Override
    public void onGetMoviesError(String error) {
        Snackbar.make(containerMainActivity, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onEndScroll() {
        getMoviesBySortOption();
    }

    private void getMoviesBySortOption() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String optionSort = sharedPreferences.getString(
                getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_most_popular));


        if (optionSort.equals(getString(R.string.pref_sort_most_popular))) {
            mainPresenter.getPopularMovies();
        } else {
            mainPresenter.getTopRatedMovies();
        }
    }
}
