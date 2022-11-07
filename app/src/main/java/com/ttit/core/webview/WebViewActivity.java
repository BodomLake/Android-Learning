package com.ttit.core.webview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ttit.helloworld.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tvTitle;
    private long exitTime = 0;

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        // tvTitle = findViewById(R.id.tv_title);
        webView = findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient() {
            // 这里设置获取到的网站title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
                // tvTitle.setText(title);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // 开启调试模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);  //设定支持viewport
        settings.setLoadWithOverviewMode(true);  //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);  //设定支持缩放
        settings.setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        // 在 浏览器 的 window对象上 挂上一个 android 属性
        webView.addJavascriptInterface(new JsInteraction(), "android"); // JsInteraction
        webView.loadUrl("https://m.baidu.com/");  //调用loadUrl方法为WebView加入链接
        // webView.loadUrl("http://192.168.229.1:5173/");  //调用loadUrl方法为WebView加入链接
    }

    // 我们需要重写回退按钮的时间,当用户点击回退按钮：
    // 1.webView.canGoBack()判断网页是否能后退,可以则goback()
    // 2.如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        invokeJsFn("sayHello", null);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }
    //
    public class JsInteraction {
        // 相当于 window.android.toastMessage() 的回调
        @JavascriptInterface
        public void toastMessage(String message) {   //提供给js调用的方法
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    // 执行 javascript:functionName()
    public void invokeJsFn(String functionName, Object[] args) {
        webView.loadUrl("javascript:" + functionName + "(say)");
    }
}
