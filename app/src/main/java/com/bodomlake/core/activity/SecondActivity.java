package com.bodomlake.core.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bodomlake.helloworld.R;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onCreate() ===");
        setContentView(R.layout.second_activity_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onStart() ===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onResume() ===");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onPause() ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onStop() ===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onRestart() ===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("bodomlake", "执行生命周期函数:=== SecondActivity: onDestroy() ===");
    }
}
