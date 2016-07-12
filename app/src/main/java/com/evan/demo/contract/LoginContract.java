package com.evan.demo.contract;

/**
 * 登陆界面接口协议
 * Created by evanyu on 16/5/30.
 */
public interface LoginContract extends BaseContract {

    interface View extends IBaseView {

        /** 获取用户名 */
        String getUserName();

        /** 获取密码 */
        String getUserPwd();

        /** 提示账号或密码为空 */
        void showEmptyPrompt();

        /** 提示正在登陆 */
        void showLoginProgress();

        /** 隐藏正在登陆的提示 */
        void hideLoginProgress();

        /** 提示登陆失败 */
        void promptLoginFailed();

    }

    interface Presenter extends IBasePresenter {

        void onCreate();
        void onDestroy();

        /** 登陆 */
        void login();

        /**
         * 记录或取消记录帐号和密码
         * @param isRemember 是否记录
         * @return true:操作成功, false:操作失败
         */
        boolean rememberAccAndPwd(boolean isRemember);
    }

}
