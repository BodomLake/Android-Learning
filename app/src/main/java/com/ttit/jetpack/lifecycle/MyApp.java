package com.ttit.jetpack.lifecycle;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 全局的获取LifeCycle
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppObserver());
    }
}
