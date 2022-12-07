package com.bodomlake.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.LifecycleService;

public class MyLocationService extends LifecycleService {
    // 构造开始，就追加对设备的位置的监听
    public MyLocationService() {
        Log.e("bodomlake", "MyLocationService init");
        // locationObserver 是一个 实现了 LifecycleObserver 接口的类的实例对象
        MyLocationObserver locationObserver = new MyLocationObserver(this);
        // 让 locationObserver 监听 本Service 的生命周期钩子
        getLifecycle().addObserver(locationObserver);
    }
}
