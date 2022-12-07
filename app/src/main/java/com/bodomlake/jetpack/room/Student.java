package com.bodomlake.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {

    // 自增主键
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public Integer id;

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    public Integer age;

    @ColumnInfo(name = "gender", typeAffinity = ColumnInfo.INTEGER)
    public int gender;

    // @ColumnInfo(name = "score", typeAffinity = ColumnInfo.INTEGER)
    // public int score;

    @Ignore
    public boolean flag;

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // room会忽略这种被标记的构造方法
    @Ignore
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Ignore
    public Student(String name) {
        this.name = name;
    }

    @Ignore
    public Student(Integer id) {
        this.id = id;
    }
}
