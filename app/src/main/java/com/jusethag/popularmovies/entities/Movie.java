package com.jusethag.popularmovies.entities;

import com.google.gson.annotations.SerializedName;
import com.jusethag.popularmovies.api.MovieClient;

/**
 * Created by JusethAg on 9/1/2016.
 */
public class Movie {

    private String title;
    private String overview;
    @SerializedName("poster_path")
    private String imageUrl;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("release_date")
    private String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String urlImage) {
        this.imageUrl = MovieClient.BASE_IMAGE_URL + urlImage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
