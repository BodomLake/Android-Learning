package com.ttit.jetpack.databinding;

import androidx.databinding.ObservableField;

// ObservableField包裹的对象
public class UserViewModel2 {
    private ObservableField<User> userObservableField;

    public UserViewModel2() {
        if (userObservableField == null) {
            User user = new User("鞠婧祎");
            this.userObservableField = new ObservableField<User>();
            userObservableField.set(user);
        }
    }

    public String getUserName() {
        return userObservableField.get().getName();
    }

    public void setUserName(String name) {
        userObservableField.get().setName(name);
    }
}
