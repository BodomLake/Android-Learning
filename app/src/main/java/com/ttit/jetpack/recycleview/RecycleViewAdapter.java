package com.ttit.jetpack.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ttit.helloworld.R;
import com.ttit.helloworld.databinding.ActivityRecycleViewBinding;
import com.ttit.helloworld.databinding.RecycleViewItemBinding;

import java.util.List;

// DataBinding 和 RecycleViewAdapter 的配合使用
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<Idol> idolList;
    Integer recycleViewItem = R.layout.recycle_view_item;

    // 入参初始化
    public RecycleViewAdapter(List<Idol> idolList) {
        this.idolList = idolList;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private RecycleViewItemBinding rviBinding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        // 给出自定义的databinding的根节点，作为 自定义的ViewHolder的构造入参
        public MyViewHolder(RecycleViewItemBinding rviBinding) {
            // 获取根节点
            super(rviBinding.getRoot());
            this.rviBinding = rviBinding;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleViewItemBinding rviBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), recycleViewItem, parent, false
        );
        return new MyViewHolder(rviBinding);
    }

    // onBindViewHolder 返回了 View的序号（position）
    // viewHolder的dataBinding 设置 数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Idol idol = idolList.get(position);
        holder.rviBinding.setIdol(idol);
    }

    @Override
    public int getItemCount() {
        return idolList.size();
    }
}
