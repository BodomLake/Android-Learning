package com.ttit.jetpack.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.ttit.helloworld.R;
import com.ttit.helloworld.databinding.ActivityRecycleViewBinding;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_recycle_view.xml 采取了 schemas.android.com/apk/res-auto 这个命名空间 启用了 DataBinding
        ActivityRecycleViewBinding arvBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_recycle_view);
        // 设置为线性布局
        arvBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        // 给DataBinding设置 我们自定义的适配器 RecycleViewAdapter
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(IdolUtil.getIdols());
        arvBinding.recycleView.setAdapter(recycleViewAdapter);
        // recycleViewAdapter.notifyDataSetChanged();
    }
}