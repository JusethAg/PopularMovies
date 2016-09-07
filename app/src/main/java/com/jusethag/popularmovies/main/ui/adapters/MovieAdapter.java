package com.jusethag.popularmovies.main.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusethag.popularmovies.R;
import com.jusethag.popularmovies.models.Movie;
import com.jusethag.popularmovies.libs.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private OnRecyclerScrollListener onRecyclerScrollListener;

    public MovieAdapter(List<Movie> movies, ImageLoader imageLoader,
                        OnItemClickListener onItemClickListener,
                        OnRecyclerScrollListener onRecyclerScrollListener) {
        this.movies = movies;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.onRecyclerScrollListener = onRecyclerScrollListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void updateMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        movie.setImageUrl(movie.getImageUrl());

        imageLoader.load(holder.thumbnailMovie, movie.getImageUrl());
        holder.titleMovie.setText(movie.getTitle());
        holder.setOnItemClickListener(movie, this.onItemClickListener);
        if (position == getItemCount() - 1) {
            onRecyclerScrollListener.onEndScroll();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail_movie)
        ImageView thumbnailMovie;
        @BindView(R.id.title_movie)
        TextView titleMovie;

        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Movie movie,
                                           final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }


}
