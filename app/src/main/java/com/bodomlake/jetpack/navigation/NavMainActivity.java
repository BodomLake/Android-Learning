package com.bodomlake.jetpack.navigation;

import android.app.PendingIntent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bodomlake.helloworld.R;

// Navigation-Graph() -> NavHostFragment(Content_Nav) -> Fragment
// Activity布局中引入<include layout="@layout/content_nav_main" /> 也就是指定了 NavHostFragment
// NavHostFragment的布局文件中指定了 Fragment和Fragment之间的跳转动作<action app:destination="">
public class NavMainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // 控制器 nav_host_fragment_content_nav_main
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);

        // 找到 控制器对应的graph（引导图），构建出应用的导航栏的相关配置
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        // 给导航栏匹配上 控制器和导航栏配置
        // 这样的话，导航栏会
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}