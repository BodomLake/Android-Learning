package com.bodomlake.jetpack.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.bodomlake.helloworld.R;
import com.bodomlake.helloworld.databinding.ActivityPagingBinding;


public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_recycle_view.xml 采取了 schemas.android.com/apk/res-auto 这个命名空间 启用了 DataBinding
        ActivityPagingBinding arvBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);
        // 设置为线性布局
        arvBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 给DataBinding设置 我们自定义的适配器 RecycleViewAdapter
        RecycleViewAdapter recyclerViewAdapter = new RecycleViewAdapter(IdolUtil.getIdols());
        arvBinding.recyclerView.setAdapter(recyclerViewAdapter);
        // recycleViewAdapter.notifyDataSetChanged();
    }
}