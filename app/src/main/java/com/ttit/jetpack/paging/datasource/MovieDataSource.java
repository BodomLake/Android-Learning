package com.ttit.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.ttit.jetpack.paging.request.RetrofitClient;
import com.ttit.jetpack.paging.model.Movie;
import com.ttit.jetpack.paging.model.Movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// PositionalDataSource的使用
public class MovieDataSource extends PositionalDataSource<Movie> {

    public static final int PER_PAGE = 8;

    // 页面首次加载
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Movie> callback) {
        int startPosition = 0;
        RetrofitClient.getInstance().getAPI().getMovies(startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Movies movies = response.body();
                        if (movies != null) {
                            callback.onResult(movies.movieList, movies.start, movies.count);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        callback.onResult(new ArrayList<>(1), 0, 0);
                    }
                });
    }

    // 下一页加载
    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {
        int startPosition = params.startPosition;
        RetrofitClient.getInstance().getAPI().getMovies(startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Movies movies = response.body();
                        if (movies != null) {
                            callback.onResult(movies.movieList);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        callback.onResult(new ArrayList<>(1));
                    }
                });
    }
}
