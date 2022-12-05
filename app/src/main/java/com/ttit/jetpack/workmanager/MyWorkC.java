package com.ttit.jetpack.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorkC extends Worker {
    public MyWorkC(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("bodomlake", this.getClass().getSimpleName() + " doing Work");
        return Result.success();
    }
}
