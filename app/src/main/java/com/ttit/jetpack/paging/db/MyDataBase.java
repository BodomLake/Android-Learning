package com.ttit.jetpack.paging.db;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ttit.jetpack.paging.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {

    private static MyDataBase instance;
    public static final String DATABASE_NAME = "movie.db";

    // private MyDataBase(){};

    /**
     * @param context 作为数据库实例的上下文
     * @return
     */
    public static synchronized MyDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }

    // 会被 androidx.room:room-compiler 自动实现
    public abstract MovieDao getMovieDao();
}
