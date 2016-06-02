package com.evan.demo.data.engine;

import com.evan.demo.data.bean.User;

/**
 * Created by evanyu on 16/5/30.
 */
public interface IUserEngine {

    interface OnLoginListener {
        void loginSuccess(User user);
        void loginFailed();
    }

    void login(String name, String pwd, OnLoginListener listener);

}
