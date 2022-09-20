package com.ttit.jetpack.databinding;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ttit.helloworld.BR;

// 要继承 BaseObservable
public class UserViewModel extends BaseObservable {
    private User user;

    public UserViewModel() {
        this.user = new User("Xin Zhao");
    }

    // 双向绑定
    @Bindable
    public String getUserName() {
        return this.user.getName();
    }

    public void setUserName(String userName) {
        if (!TextUtils.equals(userName, user.getName())) {
            this.user.setName(userName);
            Log.e("ttit", "set UserName:" + userName);
            notifyPropertyChanged(BR.userName);
        }
    }
}
