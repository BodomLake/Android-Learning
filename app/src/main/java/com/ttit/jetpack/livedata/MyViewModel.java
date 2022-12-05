package com.ttit.jetpack.livedata;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// 一个单向绑定的实例
public class MyViewModel extends ViewModel {
    // 定义一些 LiveData
    private MutableLiveData<Integer> currentSecond;
    private MutableLiveData<Integer> progress;

    public MutableLiveData<Integer> getCurrentSecond() {
        if (currentSecond != null) {
            currentSecond = new MutableLiveData<Integer>();
            currentSecond.setValue(0);
        }
        return currentSecond;
    }

    public void setCurrentSecond(Integer sec) {
        this.currentSecond.setValue(sec);
    }

    protected void onCleared() {
        Log.e("bodomlake", "MyViewModel:已经被清除");
    }
}
