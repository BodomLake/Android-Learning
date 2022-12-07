package com.bodomlake.jetpack.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bodomlake.helloworld.R;

public class LifeCycleActivity extends AppCompatActivity {

    // 已经实现了 LifecycleObserver 接口的组件
    private MyChronometer chronometer;

    // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        chronometer = (MyChronometer) findViewById(R.id.myChronometer);
        // 让 chronometer 监听本 activity 的生命周期钩子
        getLifecycle().addObserver(chronometer);
    }

    public void startGPSLocation() {
        startService(new Intent(this, MyLocationService.class));
    }

    public void stopGPSLocation() {
        stopService(new Intent(this, MyLocationService.class));
    }
}