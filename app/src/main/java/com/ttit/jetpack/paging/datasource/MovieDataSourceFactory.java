package com.ttit.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.ttit.jetpack.paging.model.Movie;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        return new MovieDataSource();
    }
}
