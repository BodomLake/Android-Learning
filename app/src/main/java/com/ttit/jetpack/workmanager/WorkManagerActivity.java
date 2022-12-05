package com.ttit.jetpack.workmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ttit.helloworld.R;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WorkManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void doWork(View view) {
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        // workManager.beginWith().then();
        Data inputData = new Data.Builder()
                .putInt("age", 100)
                .putString("name", "Jack")
                .build();

        // 对请求添加的约束
        Constraints myConstraints = new Constraints.Builder()
                // 待机状态下执行
                .setRequiresDeviceIdle(true)
                // 设置为 NetworkType.NOT_REQUIRED 不需要网络也能执行
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                // 需要电池不低的状态
                .setRequiresBatteryNotLow(true)
                // 在存储容量较低的情况下执行
                .setRequiresStorageNotLow(true)
                // 需要出于充电状态
                .setRequiresCharging(true)
                .build();
        // 单次请求

        String requestTag = "work-request-1";

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWork.class)
                // 设置约束
                .setConstraints(myConstraints)
                // 设置延迟
                .setInitialDelay(1500, TimeUnit.MILLISECONDS)
                // 回避策略
                .setBackoffCriteria(BackoffPolicy.LINEAR, 2000, TimeUnit.MILLISECONDS)
                // 添加标签
                .addTag(requestTag)
                // 添加传参
                .setInputData(inputData)
                .build();

        workManager.enqueue(workRequest);


        // 周期性任务不能少于 15分钟
        PeriodicWorkRequest workRequest2 = new PeriodicWorkRequest.Builder(MyWork.class, 15, TimeUnit.MINUTES)
                .build();
        // workManager.enqueue(workRequest2);

        workManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Log.e("bodomlake", workInfo.getId().toString());
                // workInfo.getId();
                // 对应 doWork的 return Result.success();
                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println(workInfo.getOutputData().getString("name"));
                    // workInfo.getOutputData().getString("name")
                }
            }
        });

        // 设置一个计时器，去取消workRequest
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // workManager.cancelAllWork();
                // workManager.cancelAllWorkByTag(requestTag);
                // workManager.cancelUniqueWork();
                workManager.cancelWorkById(workRequest.getId());
            }
        }, 10000);


        // 任务组合
        OneTimeWorkRequest workA = new OneTimeWorkRequest.Builder(MyWorkA.class).build();
        OneTimeWorkRequest workB = new OneTimeWorkRequest.Builder(MyWorkB.class).build();
        OneTimeWorkRequest workC = new OneTimeWorkRequest.Builder(MyWorkC.class).build();
        OneTimeWorkRequest workD = new OneTimeWorkRequest.Builder(MyWorkD.class).build();
        OneTimeWorkRequest workE = new OneTimeWorkRequest.Builder(MyWorkE.class).build();

        // A B 为一组 WorkContinuation
        WorkContinuation workContinuation1 = WorkManager.getInstance(getApplicationContext())
                .beginWith(workA).then(workB);
        // C D 为一组 WorkContinuation
        WorkContinuation workContinuation2 = WorkManager.getInstance(getApplicationContext())
                .beginWith(workC).then(workD);
        List<WorkContinuation> workList = new ArrayList<>();
        workList.add(workContinuation1);
        workList.add(workContinuation2);
        // 最后合并任务流，连上workE，压入队列
        WorkContinuation.combine(workList).then(workE).enqueue();
    }
}