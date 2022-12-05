package com.ttit.jetpack.paging.model;

import androidx.room.Entity;

import java.util.Objects;

// 专门用于PositionalDataSource类型的API接口
@Entity(tableName = "movie")
public class Movie {
    public int id;
    public String title;
    public String rate;
    public String cover;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && title.equals(movie.title) && rate.equals(movie.rate) && cover.equals(movie.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rate, cover);
    }

}
