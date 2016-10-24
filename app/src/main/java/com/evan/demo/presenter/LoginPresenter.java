package com.evan.demo.presenter;

import android.text.TextUtils;

import com.evan.demo.model.engine.IUserEngine;
import com.evan.demo.model.engine.impl.UserEngineImpl;
import com.evan.demo.ui.iview.ILoginView;

/**
 * Created by evanyu on 16/5/30.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private IUserEngine mUserEngine = new UserEngineImpl();

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    /**
     * 执行登陆操作
     */
    public void login(String name, String pwd) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            // mView.showEmptyError();
            mView.showToast("账号或密码不能为空");
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
//                User user = newengine User(name, pwd);
                mView.onLoginSuccess();
            }

            @Override
            public void loginFailed() {
                hideLoading();
                mView.onLoginFailed();
            }
        });
    }

    @Override
    public void onViewDestory() {
        super.onViewDestory();
    }
}
