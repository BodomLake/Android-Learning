package com.bodomlake.jetpack.paging.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {
    public int count;
    public int start;
    public int total;

    @SerializedName("subjects")
    public List<Movie> movieList;

    @Override
    public String toString() {
        return "Movies{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", movieList=" + movieList +
                '}';
    }
}
