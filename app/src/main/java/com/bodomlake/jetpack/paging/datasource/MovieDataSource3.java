package com.bodomlake.jetpack.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.bodomlake.jetpack.paging.model.Movie;
import com.bodomlake.jetpack.paging.request.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// PageKeyedDataSource 的使用
// key设置为int类型，T设置为Movie也就是我们的Item
public class MovieDataSource3 extends ItemKeyedDataSource<Integer, Movie> {

    public static final int PER_PAGE = 8;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Movie> callback) {
        int since = 0;
        RetrofitClient.getInstance().getAPI().getMovies3(since, PER_PAGE)
                .enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        List<Movie> movieList = response.body();
                        if (null != movieList) {
                            callback.onResult(movieList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Movie> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Movie> callback) {
        RetrofitClient.getInstance().getAPI().getMovies3(params.key, PER_PAGE)
                .enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        List<Movie> movieList = response.body();
                        if (null != movieList) {
                            callback.onResult(movieList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Movie item) {
        // 把id作为key，在 LoadParams<Integer> params 中传递
        return item.id;
    }
}
