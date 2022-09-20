package com.ttit.jetpack.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ttit.helloworld.R;
import com.ttit.helloworld.databinding.ActivityDataBindingTestBinding;


import java.io.FileOutputStream;
import java.io.InputStream;

public class DataBindingTest extends AppCompatActivity {

    private Idol idol;
    private ImageView imageView;

    InputStream is = null;
    FileOutputStream fos = null;
    Bitmap image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String idolName = "Xin Zhao";
        // setTitle("JetPack太拉了");

        // AppActivity 去掉标题栏，但是还是会显示系统的状态栏
        // 要放在 setContentView(R.layout.?)之前。
        // requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_data_binding_test);


        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //去掉系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        idol = new Idol();
        idol.setAge(28);
        idol.setName(idolName);
        // idol.setLocalImage(R.drawable.bunny_green);
        // idol.setNetworkImage(
        //         "https://t10.baidu.com/it/u=1103695762,183762357&fm=30&app=106&" +
        //                 "size=f242,162&n=0&g=0n&f=JPEG?s=A08B955540610305A41C282C0300E04A" +
        //                 "&sec=1663294651&t=116eaed1bef67ee1e45daa8ba976694d"
        // );
        idol.setNetworkImage("https://i1.hdslb.com/bfs/archive/cff2600140b6ba8bff2501b7af4c4dcfcde4c929.jpg@672w_378h_1c.webp");

        ActivityDataBindingTestBinding activityDataBindingTestBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test);


        activityDataBindingTestBinding.setIdol(idol);
        activityDataBindingTestBinding.setUserModelView(new UserViewModel());


        // 原生方法加载assets文件夹下面的
        // imageView = findViewById(R.id.imageView);
        // String pictureName = "image/jjy.jpg";
        // AssetManager am = this.getResources().getAssets();
        // String filePath = getFilesDir().getAbsolutePath() + "/image/jjy.jpg";
        // Log.e("ttit-tag", filePath);
        // try {
        //     InputStream is = am.open(pictureName);
        //     image = BitmapFactory.decodeStream(is);
        //     Log.e("ttit-tag", image.toString());
        //     // imageView.setBackground(new BitmapDrawable(getResources(), image));
        //     imageView.setImageBitmap(image);
        //     is.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }


    }
}