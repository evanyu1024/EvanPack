package com.evan.demo.presenter;

import android.text.TextUtils;

import com.evan.demo.model.engine.IUserEngine;
import com.evan.demo.model.engine.impl.UserEngineImpl;
import com.evan.demo.ui.ILoginView;
import com.evan.demo.manager.utils.SPUtils;

/**
 * Created by evanyu on 16/5/30.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private IUserEngine mUserEngine = new UserEngineImpl();

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    @Override
    public void start() {
        loadRememberedAccAndPwd();
    }

    /**
     * 执行登陆操作
     */
    public void login() {
        String name = mView.getUserName();
        String pwd = mView.getUserPwd();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            mView.showEmptyError();
            return;
        }

        showLoading();

        mUserEngine.login(name, pwd, new IUserEngine.OnLoginListener() {
            @Override
            public void loginSuccess(String data) {
                hideLoading();
                // 从model获取到的是原始数据,必要时可在P层对数据进行处理并传递给View层
//                String[] infoArr = data.split(",");
//                String name = infoArr[0];
//                String pwd = infoArr[1];
//                User user = new User(name, pwd);
                mView.onLoginSuccess();
            }

            @Override
            public void loginFailed() {
                hideLoading();
                mView.onLoginFailed();
            }
        });
    }

    /**
     * 记录或取消记录帐号和密码
     *
     * @param isRemember 是否记录
     * @return true:操作成功, false:操作失败
     */
    public void rememberAccAndPwd(boolean isRemember) {
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

    /**
     * 当View被销毁时调用
     */
    @Override
    public void onViewDestroy() {

    }

}
