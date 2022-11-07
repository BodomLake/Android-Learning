package com.ttit.jetpack.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    private MyDataBase myDataBase;
    private StudentDao studentDao;

    public StudentRepository(Context context) {
        // if (context != null)
            myDataBase = MyDataBase.getInstance(context);
        // if (myDataBase != null)
            studentDao = myDataBase.getStudentDao();
    }

    // 获取所有学生
    public LiveData<List<Student>> getAllStudentsLive() {
        return studentDao.getAllStudent();
    }

    // 插入一条学生的数据
    public void insertStudent(Student... students) {
        new InsertStudent(studentDao).execute(students);
    }

    class InsertStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        public InsertStudent(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    // 更新学生的数据
    public void mUpdate(Student student) {
        // Student student = new Student("Jackson", 200);
        new UpdateStudent(studentDao).execute(student);
    }

    // AsyncTask<Params, Progress, Result> 三个泛型分别对应着 入参，进度，结果
    class UpdateStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        public UpdateStudent(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students);
            // studentRecycleViewAdapter.setStudentList(Arrays.asList(students));
            return null;
        }
    }

    public void mQuery(Student... students) {
        new QueryStudent(studentDao).execute(students);
    }

    class QueryStudent extends AsyncTask<Student, Void, List<Student>> {
        private StudentDao studentDao;

        public QueryStudent(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected List<Student> doInBackground(Student... students) {
            List<Student> studentList = studentDao.getStudentById(2);
            // 返给适配器，设置私有数据
            // studentRecycleViewAdapter.setStudentList(studentList);
            return studentList;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            // 主动触发适配器的私有数据变化->联动UI的变化
            // studentRecycleViewAdapter.notifyDataSetChanged();
        }
    }

    public void mDelete(Student... students) {
        // Student student = new Student(1 );
        new DeleteStudent(studentDao).execute(students);
    }

    class DeleteStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        public DeleteStudent(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students);
            return null;
        }
    }

    public void deleteAll() {
        new DeleteAllStudents(studentDao).execute();
    }

    class DeleteAllStudents extends AsyncTask<Void, Void, Void> {

        private StudentDao studentDao;

        public DeleteAllStudents(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudent();
            return null;
        }
    }

}
