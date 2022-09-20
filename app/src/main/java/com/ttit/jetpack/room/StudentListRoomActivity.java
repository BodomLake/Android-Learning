package com.ttit.jetpack.room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ttit.helloworld.R;
import com.ttit.helloworld.databinding.ActivityStudentListRoomBinding;
import com.ttit.helloworld.databinding.RecycleViewItemBinding;
import com.ttit.jetpack.recycleview.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Activity/Fragment -> ViewModel(observe) -> Repository -> Room -> db file
// Activity/Fragment -> ViewModel(observe) -> Repository -> Retrofit -> webservice data
public class StudentListRoomActivity extends AppCompatActivity {

    private StudentRecycleViewAdapter studentRecycleViewAdapter;
    private StudentDao studentDao;
    private MyDataBase myDataBase;
    private StudentRepository studentRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.id.activity_student_list_room);

        ActivityStudentListRoomBinding aslrBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_student_list_room);
        aslrBinding.studentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "SomePeople", 123));
        // students.add(new Student(2, "SomePeople", 123));
        studentRecycleViewAdapter = new StudentRecycleViewAdapter(students);
        // 设置RecycleView的适配器
        aslrBinding.studentRecycleView.setAdapter(studentRecycleViewAdapter);

        ViewModelProvider.AndroidViewModelFactory vmpf =
                new ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider vmp = new ViewModelProvider(this, vmpf);
        StudentViewModel stuVM = vmp.get(StudentViewModel.class);

        stuVM.getAllStudentLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentRecycleViewAdapter.setStudentList(students);
                studentRecycleViewAdapter.notifyDataSetChanged();
            }
        });
    }

    public void mInsert(View view) {
        Student student1 = new Student("Jack", 20);
        Student student2 = new Student("Slow Fuck", 419);
        studentRepository.insertStudent(student1, student2);
    }

    public void mUpdate(View view) {
        Student student = new Student(1, "Jackson", 200);
        studentRepository.mUpdate(student);
    }

    public void mDelete(View view) {
        Student student = new Student(1, "Jackson", 200);
        studentRepository.mDelete(student);
    }


    public void mQuery(View view) {
        studentRepository.mQuery();
    }

    public void clearAllStudent(View view) {
        studentRepository.deleteAll();
    }

}
