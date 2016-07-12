package com.evan.demo.presenter;

import android.text.TextUtils;

import com.evan.demo.contract.LoginContract;
import com.evan.demo.model.bean.User;
import com.evan.demo.model.engine.IUserEngine;
import com.evan.demo.model.engine.impl.UserEngineImpl;
import com.evan.demo.utils.SPUtils;

/**
 * Created by evanyu on 16/5/30.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private IUserEngine mUserEngine = new UserEngineImpl();

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void onCreate() {
        loadRememberedAccAndPwd();
    }

    @Override
    public void login() {
        String name = mView.getUserName();
        String pwd = mView.getUserPwd();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            mView.showEmptyPrompt();
            mView.hideLoginProgress();
            return;
        }

        mView.showLoginProgress();
        mUserEngine.login(name, pwd, new IUserEngine.OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                // 登陆成功
                mView.hideLoginProgress();
                rememberAccAndPwd(mView.isRememberAccAndPwd());
            }

            @Override
            public void loginFailed() {
                mView.hideLoginProgress();
                mView.promptLoginFailed();
            }
        });
    }

    /**
     * 记录或取消记录帐号和密码
     * @param isRemember 是否记录
     * @return true:操作成功, false:操作失败
     */
    private void rememberAccAndPwd(boolean isRemember) {
        if (isRemember) {
            // 记住密码
            String name = mView.getUserName();
            String pwd = mView.getUserPwd();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                SPUtils.put("userName", name);
                SPUtils.put("userPwd", pwd); // 真实项目中保存在本地的密码需要进行加密处理
            }
        } else {
            // 清空sp中记录的帐号和密码
            SPUtils.remove("userName");
            SPUtils.remove("userPwd");
        }
    }

    /**
     * 加载上次登陆时记录的帐号和密码
     */
    private void loadRememberedAccAndPwd() {
        String name = SPUtils.get("userName", null);
        String pwd = SPUtils.get("userPwd", null);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            mView.setCboxPwdChecked(true);
            mView.setUserName(name);
            mView.setUserPwd(pwd);
        }
    }

    @Override
    public void onDestroy() {

    }
}
