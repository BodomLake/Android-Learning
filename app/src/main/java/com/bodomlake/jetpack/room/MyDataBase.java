package com.bodomlake.jetpack.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, version = 2, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase instance;
    public static final String DATABASE_NAME = "Student.db";

    // private MyDataBase(){};

    /**
     * @param context 作为数据库实例的上下文
     * @return
     */
    public static synchronized MyDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    // 允许在主线程中执行SQL语句
                    // 由于我们在 StudentRepository 设计了AsyncTask做为异步任务
                    // .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    // .createFromAsset("?.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    //version从1到2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `student` ADD COLUMN gender INTEGER NOT NULL DEFAULT 1");
        }
    };

    //version从2到3
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN score INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 直接把version从1到3
    static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN gender INTEGER NOT NULL DEFAULT 1");
            database.execSQL("ALTER TABLE student ADD COLUMN score INTEGER NOT NULL DEFAULT 1");
        }
    };

    // 会被 androidx.room:room-compiler 自动实现
    public abstract StudentDao getStudentDao();
}
