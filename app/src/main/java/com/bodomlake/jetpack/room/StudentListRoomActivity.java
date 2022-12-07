package com.bodomlake.jetpack.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bodomlake.helloworld.R;
import com.bodomlake.helloworld.databinding.ActivityStudentListRoomBinding;

import java.util.ArrayList;
import java.util.List;

// Activity/Fragment -> ViewModel(observe) -> Repository -> Room -> db file
// Activity/Fragment -> ViewModel(observe) -> Repository -> Retrofit -> webservice data
public class StudentListRoomActivity extends AppCompatActivity {

    private StudentRecycleViewAdapter studentRecycleViewAdapter;
    private StudentViewModel stuVM;
    //  private StudentDao studentDao;
    //  private MyDataBase myDataBase;
    //  private StudentRepository studentRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.id.activity_student_list_room);
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "SomePeople", 123));

        ActivityStudentListRoomBinding aslrBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_student_list_room);

        Log.e("bodomlake-student", String.valueOf(students.size()));
        // students.add(new Student(2, "SomePeople", 123));
        // 给 RecycleAdapter 填充 数据
        studentRecycleViewAdapter = new StudentRecycleViewAdapter(null);

        // 设置RecycleView的适配器
        aslrBinding.studentRecycleView.setAdapter(studentRecycleViewAdapter);
        aslrBinding.studentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        ViewModelProvider.AndroidViewModelFactory vmpf =
                new ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider vmp = new ViewModelProvider(this, vmpf);
        // vmp作为 viewmodel的工厂，根据 class文件 生产（构造、实例化）出了 stuVM这一类的对象
        stuVM = vmp.get(StudentViewModel.class);

        // 对 getAllStudentLiveData() 返回的 LiveData<T>类型数据 添加观察者，一旦发现被执行，就发起回调
        stuVM.getAllStudentLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                // 影响适配器
                studentRecycleViewAdapter.setStudentList(students);
                // 提醒对应的item做出视图更新
                studentRecycleViewAdapter.notifyDataSetChanged();
            }
        });

    }

    public void mInsert(View view) {
        Student student1 = new Student("Jack", 20);
        Student student2 = new Student("Slow Fuck", 999);
        Student student3 = new Student("Rose", 18);
        // studentRepository.insertStudent(student1, student2, student3);
        // 直接操作 viewmodel数据就可以联动视图层
        stuVM.insertStudent(student1, student2, student3);
    }

    public void mUpdate(View view) {
        Student student = new Student(1, "Jackson", 200);
        // studentRepository.mUpdate(student);
        stuVM.updateStudent(student);
    }

    public void mDelete(View view) {
        Student student = new Student(1);
        // studentRepository.mDelete(student);
        stuVM.updateStudent(student);
    }


    public void mQuery(View view) {
        // studentRepository.mQuery();
        stuVM.getAllStudentLiveData();
    }

    public void clearAllStudent(View view) {
        // studentRepository.deleteAll();
        stuVM.deleteAllStudent();
    }

}
