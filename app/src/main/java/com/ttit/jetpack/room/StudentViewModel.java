package com.ttit.jetpack.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;

    // 在构造的时候，绑定了整个应用的上下文
    public StudentViewModel(@NonNull Application application) {
        super(application);
        this.studentRepository = new StudentRepository(application);
    }

    public void insertStudent(Student... students){
        studentRepository.insertStudent(students);
    }

    public void deleteAllStudent() {
        studentRepository.deleteAll();
    }

    public void deleteStudent(Student... students) {
        studentRepository.mDelete(students);
    }

    public LiveData<List<Student>> getAllStudentLiveData() {
        return studentRepository.getAllStudentsLive();
    }

    public void updateStudent(Student student){
        studentRepository.mUpdate(student);
    }
}
