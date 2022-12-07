package com.bodomlake.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.bodomlake.jetpack.paging.model.Movie;

import kotlin.coroutines.Continuation;

// PositionalDataSource的使用
public class MovieDataSource4 extends PagingSource {

    public static final int PER_PAGE = 8;

    @Nullable
    @Override
    public Movie getRefreshKey(@NonNull PagingState pagingState) {
        // pagingState.
        return null;
    }

    @Nullable
    @Override
    public Object load(@NonNull LoadParams loadParams, @NonNull Continuation continuation) {
        return null;
    }
}
