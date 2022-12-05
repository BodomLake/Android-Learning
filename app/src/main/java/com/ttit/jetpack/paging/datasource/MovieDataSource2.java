package com.ttit.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ttit.jetpack.paging.model.Movie;
import com.ttit.jetpack.paging.model.Movies;
import com.ttit.jetpack.paging.model.Movies2;
import com.ttit.jetpack.paging.request.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// PageKeyedDataSource 的使用
public class MovieDataSource2 extends PageKeyedDataSource<Integer, Movie> {

    public static final int PER_PAGE = 8;
    public static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {

        RetrofitClient.getInstance().getAPI().getMovies2(FIRST_PAGE, PER_PAGE)
                .enqueue(new Callback<Movies2>() {
                    @Override
                    public void onResponse(Call<Movies2> call, Response<Movies2> response) {
                        Movies2 movies2 = response.body();
                        if (null != movies2) {
                            // previousPageKey在当前第一页的情况下，是没有上一页的说法的
                            // 指定下一页为第二页(nextPageKey)
                            callback.onResult(movies2.movieList, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies2> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        // params.key
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        RetrofitClient.getInstance().getAPI().getMovies2(params.key, PER_PAGE)
                .enqueue(new Callback<Movies2>() {
                    @Override
                    public void onResponse(Call<Movies2> call, Response<Movies2> response) {
                        Movies2 movies2 = response.body();
                        if (null != movies2) {
                            // hasMore为false就不制定
                            int nextKey = movies2.hasMore ? params.key + 1 : null;
                            callback.onResult(movies2.movieList, nextKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies2> call, Throwable t) {

                    }
                });
    }
}
