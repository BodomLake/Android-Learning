package com.ttit.jetpack.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase instance;
    public static final String DATABASE_NAME = "Stu.db";

    // private MyDataBase(){};

    /**
     *
     * @param context 作为数据库实例的上下文
     * @return
     */
    public static synchronized MyDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    // 允许在主线程中执行SQL语句
                    // .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    // 会被 androidx.room:room-compiler 自动实现
    public abstract StudentDao getStudentDao();
}
