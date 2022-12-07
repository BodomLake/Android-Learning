package com.bodomlake.jetpack.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.bodomlake.helloworld.R;
import com.bodomlake.helloworld.databinding.ActivityPagingBinding;
import com.bodomlake.jetpack.paging.adapter.MoviePagedListAdapter;
import com.bodomlake.jetpack.paging.model.MovieViewModel;

public class PagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_paging);
        ActivityPagingBinding activityPagingBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_paging);
        activityPagingBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PagedListAdapter pagedListAdapter = new MoviePagedListAdapter(this);
        // PagingDataAdapter
        activityPagingBinding.recyclerView.setAdapter(pagedListAdapter);

        MovieViewModel movieViewModel = new ViewModelProvider(
                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(MovieViewModel.class);

        movieViewModel.moviePagedList.observe(this, movies -> {
            // AsyncPagedListDiffer
            pagedListAdapter.submitList(movies);
        });

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefreshLayout);
        swipeRefresh.setOnRefreshListener(() -> {
            // 清空数据库(缓存)，重新请求，请求新的网络数据
            movieViewModel.refresh();
            // 清空了之后，就设置为不在刷新状态了
            swipeRefresh.setRefreshing(false);
        });
    }
}