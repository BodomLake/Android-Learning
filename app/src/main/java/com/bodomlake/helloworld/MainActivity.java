package com.bodomlake.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bodomlake.helloworld.R;

public class MainActivity extends AppCompatActivity {
    private Button btnOne, btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_btn);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {  //按钮绑定点击事件
            @Override
            public void onClick(View v) {
                if (btnTwo.getText().toString().equals("按钮不可用")) {
                    btnOne.setEnabled(false);
                    btnTwo.setText("按钮可用");
                } else {
                    btnOne.setEnabled(true);
                    btnTwo.setText("按钮不可用");
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFoucs) {
        super.onWindowFocusChanged(hasFoucs);
        Button btn = findViewById(R.id.btn);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        // EditText et = (EditText) new Object();
        // et.setSelection(2, 3);
        // et.requestFocus();
    }


}
