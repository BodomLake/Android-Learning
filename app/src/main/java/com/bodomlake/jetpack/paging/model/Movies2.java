package com.bodomlake.jetpack.paging.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// 专门用于PagedKeyDataSource类型的API接口
public class Movies2 {

    @SerializedName("has_more")
    public boolean hasMore;

    @SerializedName("subjects")
    public List<Movie> movieList;

    @Override
    public String toString() {
        return "Movies2{" +
                "hasMore=" + hasMore +
                ", movieList=" + movieList +
                '}';
    }
}
