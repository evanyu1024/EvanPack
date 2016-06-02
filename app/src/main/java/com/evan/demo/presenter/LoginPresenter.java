package com.evan.demo.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.evan.demo.contract.LoginContract;
import com.evan.demo.data.bean.User;
import com.evan.demo.data.engine.IUserEngine;
import com.evan.demo.data.engine.impl.UserEngineImpl;

/**
 * Created by evanyu on 16/5/30.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private Handler mHandler = new Handler();
    private IUserEngine mUserEngine = new UserEngineImpl();

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void login() {
        String name = mView.getUserName();
        String pwd = mView.getUserPwd();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            mView.showEmptyPrompt();
            mView.hideLoginProgress();
            return;
        }

        mView.showLoginProgress();
        mUserEngine.login(name, pwd, new IUserEngine.OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                // 登陆成功
                mView.enterInMainActivity();
                mView.hideLoginProgress();
            }

            @Override
            public void loginFailed() {
                mView.showLoginFailedPrompt();
                mView.hideLoginProgress();
            }
        });
    }

    @Override
    public void clear() {

    }
}
