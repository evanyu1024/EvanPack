package com.evan.demo.model.engine;

/**
 * Created by evanyu on 16/5/30.
 */
public interface IUserEngine {

    interface OnLoginListener {
        void loginSuccess(String data);
        void loginFailed();
    }

    void login(String name, String pwd, OnLoginListener listener);

}
