package com.ttit.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class AppObserver implements LifecycleObserver {
    private final String Tag = "bodomlake";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE() {
        Log.e(Tag, "Lifecycle.Event.ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START() {
        Log.e(Tag, "Lifecycle.Event.ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME() {
        Log.e(Tag, "Lifecycle.Event.ON_RESUME");
    }

    // 永远不被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY() {
        Log.e(Tag, "Lifecycle.Event.ON_DESTROY");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE() {
        Log.e(Tag, "Lifecycle.Event.ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP() {
        Log.e(Tag, "Lifecycle.Event.ON_STOP");
    }
}
