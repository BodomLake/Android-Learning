package com.ttit.jetpack.livedata;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ttit.helloworld.R;

import java.util.Timer;
import java.util.TimerTask;

// 单项绑定实例
public class ViewModelActivity extends AppCompatActivity {

    private ViewModelProvider vmp;
    private MyViewModel vm;
    private TextView tv;
    private Timer timer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        // VMP的 安卓 VMP 工厂(avmFactory)
        ViewModelProvider.AndroidViewModelFactory avmFactory =
                new ViewModelProvider.AndroidViewModelFactory(getApplication());
        vmp = new ViewModelProvider(this, avmFactory);
        vm = vmp.get(MyViewModel.class);
        tv.setText(vm.getCurrentSecond().getValue());
        // vm.getCurrentSecond().postValue();
        // 添加观察者，观察者观察到数据的变化，我们在回调中再同步到UI，textView中的数字也会随之改变
        vm.getCurrentSecond().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv.setText(vm.getCurrentSecond().getValue());
            }
        });
    }

    /**
     * 不需要和 MVP MVC版本的方案，不需要在自行开启的UI线程中发起请求
     */
    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 直接 set就好，响应式的数据
                // vm.getCurrentSecond().setValue(vm.getCurrentSecond().getValue() + 1);
                vm.setCurrentSecond(vm.getCurrentSecond().getValue() + 1);
            }
        }, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        this.timer = null;
        super.onDestroy();
    }
}
