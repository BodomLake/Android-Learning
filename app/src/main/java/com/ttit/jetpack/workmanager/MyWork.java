package com.ttit.jetpack.workmanager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWork extends Worker {

    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        SystemClock.sleep(2000);
        Log.e("bodomlake", "do Work!");
        Data inputData = getInputData();
        Data outputData = new Data.Builder()
                .putAll(inputData)
                .build();
        // 可以返回数据
        return Result.success(outputData);
        // return Result.retry();
    }
}
