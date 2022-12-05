package com.ttit.jetpack.paging.datasource;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.ttit.jetpack.paging.db.MyDataBase;
import com.ttit.jetpack.paging.model.Movie;
import com.ttit.jetpack.paging.model.Movies;
import com.ttit.jetpack.paging.request.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 本地有缓存的时候，可以用本回调类，做PagingDataSource
public class MovieBoundaryCallback extends PagedList.BoundaryCallback<Movie> {

    private static final int PER_PAGE = 8;
    private Application application;

    public MovieBoundaryCallback(Application application) {
        this.application = application;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        // 加载第一页数据
        getTopData();
    }

    private void getTopData() {
        int since = 0;
        // 和PagedKeyDataSource ItemKeyedDataSource PositionalDataSource三种数据源设定的不一样
        // 比如onZeroItemsLoaded和onItemAtEndLoaded
        // 没有给开发者设计含有 LoadInitialParams LoadInitialCallback两种参数的抽象方法，强行做callback.onResult(items)
        // 而是让开发者自己决定BoundaryCallback在Android应用中的行为
        RetrofitClient.getInstance().getAPI().getMovies3(since, PER_PAGE)
                .enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        List<Movie> movieList = response.body();
                        insertMovies(movieList);
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
    }

    private void insertMovies(List<Movie> movies) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // 存入数据库
                MyDataBase.getInstance(application).getMovieDao().insertMovies(movies);
            }
        });
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Movie movie) {
        super.onItemAtEndLoaded(movie);
        //   加载第二页数据
        getTopAfterData(movie);
    }

    private void getTopAfterData(Movie movie) {
        RetrofitClient.getInstance().getAPI().getMovies3(movie.id, PER_PAGE)
                .enqueue(new Callback<List<Movie>>() {
                             @Override
                             public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                                 List<Movie> movieList = response.body();
                                 insertMovies(movieList);
                             }

                             @Override
                             public void onFailure(Call<List<Movie>> call, Throwable t) {

                             }
                         }
                );
    }
}
