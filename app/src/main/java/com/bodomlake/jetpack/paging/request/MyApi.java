package com.bodomlake.jetpack.paging.request;

import com.bodomlake.jetpack.paging.model.Movie;
import com.bodomlake.jetpack.paging.model.Movies;
import com.bodomlake.jetpack.paging.model.Movies2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    public static final String baseURL = "getMovies";
    /**
     * 获取电影列表
     * @param since
     * @param perPage
     * @return
     */
    @GET(value = baseURL)
    Call<Movies> getMovies(
            // 不指定ip，就会成为host的后缀，正是要这样的效果
            // @Url String url,
            @Query("start") int since,
            @Query("count") int perPage
    );

    @GET(value = baseURL + "2")
    Call<Movies2> getMovies2(
            @Query("page") int page,
            @Query("pageSize") int pageSize
    );

    @GET(value = baseURL + "3")
    Call<List<Movie>> getMovies3(
            @Query("page") int page,
            @Query("pageSize") int pageSize
    );

}
