package com.bodomlake.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.bodomlake.jetpack.paging.model.Movie;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        return new MovieDataSource();
    }
}
