package com.ttit.jetpack.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ttit.helloworld.R;
import com.ttit.helloworld.databinding.RecycleViewStudentItemBinding;

import java.util.List;

public class StudentRecycleViewAdapter extends RecyclerView.Adapter<StudentRecycleViewAdapter.MyViewHolder> {

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    List<Student> studentList;
    Integer recycleViewItem = R.layout.recycle_view_student_item;

    public StudentRecycleViewAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private RecycleViewStudentItemBinding rvsiBinding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        // 给出自定义的databinding的根节点，作为 自定义的ViewHolder的构造入参
        public MyViewHolder(RecycleViewStudentItemBinding rvsiBinding) {
            // 获取根节点
            super(rvsiBinding.getRoot());
            this.rvsiBinding = rvsiBinding;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleViewStudentItemBinding recycleViewStudentItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), recycleViewItem, parent, false
        );
        return new MyViewHolder(recycleViewStudentItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.rvsiBinding.setStudent(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
