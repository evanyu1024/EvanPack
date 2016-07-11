package com.evan.demo.contract;

/**
 * 登陆界面接口协议
 * Created by evanyu on 16/5/30.
 */
public interface LoginContract extends BaseContract {

    interface View extends IBaseView<Presenter> {
        String getUserName();
        String getUserPwd();
        void clearUserName();
        void clearUserPwd();

        /** 提示账号或密码为空 */
        void showEmptyPrompt();

        /** 提示正在登陆 */
        void showLoginProgress();

        /** 隐藏正在登陆的提示 */
        void hideLoginProgress();

        /** 提示登陆失败 */
        void showLoginFailedPrompt();

        /** 进入主页面 */
        void enterMainActivity();

    }

    interface Presenter extends IBasePresenter {
        /** 登陆 */
        void login();

        /** 清空输入框 */
        void clear();
    }

}
