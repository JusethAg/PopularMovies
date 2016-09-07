package com.jusethag.popularmovies.detail.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusethag.popularmovies.PopularMoviesApp;
import com.jusethag.popularmovies.R;
import com.jusethag.popularmovies.models.Movie;
import com.jusethag.popularmovies.libs.base.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.imgToolbar)
    ImageView imgToolbar;
    @BindView(R.id.toolbarDetail)
    Toolbar toolbar;
    @BindView(R.id.title_movie)
    TextView titleMovie;
    @BindView(R.id.release_date_movie)
    TextView releaseDateMovie;
    @BindView(R.id.rating_movie)
    TextView ratingMovie;
    @BindView(R.id.synopsis_movie)
    TextView synopsisMovie;

    @Inject
    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupInjection();
        setupToolbar();

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        initializeViews(movie);
    }

    private void setupInjection() {
        PopularMoviesApp app = (PopularMoviesApp) getApplication();
        app.getDetailComponent(this).inject(this);
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void initializeViews(Movie movie) {
        imageLoader.load(imgToolbar, movie.getImageUrl());
        titleMovie.setText(movie.getTitle());
        releaseDateMovie.setText(movie.getReleaseDate());
        ratingMovie.setText(String.format(getString(R.string.rating_movie),
                String.valueOf(movie.getVoteAverage())));
        synopsisMovie.setText(movie.getOverview());
    }
}
