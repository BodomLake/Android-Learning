package com.ttit.jetpack.lifecycle;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

// 自定义的定时器组件，继承了官方提供的计时器类 Chronometer
public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedTime;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void startMeter() {
        // 获取系统
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void stopMeter() {
        setBase(SystemClock.elapsedRealtime() - getBase());
        stop();
    }
}
