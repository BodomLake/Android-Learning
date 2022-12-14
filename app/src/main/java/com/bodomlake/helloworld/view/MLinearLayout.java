package com.bodomlake.helloworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MLinearLayout extends LinearLayout {
    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("bodomlake", "MLinearLayout ://////onTouchEvent event ="+event.getAction());
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("bodomlake", "MLinearLayout ://////onInterceptTouchEvent");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("bodomlake", "MLinearLayout ://////dispatchTouchEvent");
//        return super.dispatchTouchEvent(ev);
        return true;
    }
}
