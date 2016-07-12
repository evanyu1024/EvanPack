package com.evan.demo.model.engine;

import com.evan.demo.model.bean.User;

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
