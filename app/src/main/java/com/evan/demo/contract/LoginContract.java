package com.evan.demo.contract;

import com.evan.demo.presenter.BasePresenter;
import com.evan.demo.ui.BaseView;

/**
 * Created by evanyu on 16/5/30.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        String getUserName();
        String getUserPwd();
        void clearUserName();
        void clearUserPwd();

        /**
         * 提示账号或密码为空
         */
        void showEmptyPrompt();

        /**
         * 提示正在登陆
         */
        void showLoginProgress();

        /**
         * 隐藏正在登陆的提示
         */
        void hideLoginProgress();

        /**
         * 提示登陆失败
         */
        void showLoginFailedPrompt();

        /**
         * 进入主页面
         */
        void enterInMainActivity();

    }

    interface Presenter extends BasePresenter {
        /**
         * 登陆
         */
        void login();

        /**
         * 清空输入框
         */
        void clear();
    }

}
