package com.bodomlake.core.Fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bodomlake.helloworld.R;

public class FragActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_layout);

        //动态添加Fragment到视图容器
        MyFragment2 myFragment = new MyFragment2();
        Bundle b = new Bundle();
        b.putString("name", "jack");
        myFragment.setArguments(b);

        myFragment.setCallBack(new MyFragment2.CallBack() {
            @Override
            public void getResult(String result) {
                Log.e("bodomlake", "result = " + result);
            }
        });

        // fragment 管理器
        FragmentManager fm = getSupportFragmentManager();
        // fragment 事务
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.rl_fragment, myFragment);
//        ft.replace(R.id.rl_fragment, myFragment);
//        ft.remove(myFragment);
//        ft.addToBackStack(null); //将事务添加到回退栈
        ft.commit();
    }
}
