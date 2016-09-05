package com.evan.demo.contract;

import com.evan.demo.model.bean.User;

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

        /** 设置用户名 */
        void setUserName(String name);

        /** 设置密码 */
        void setUserPwd(String pwd);

        /** 设置"记住密码"选项是否被勾选 */
        void setCboxPwdChecked(boolean checked);

        /** 是否记住密码 */
        boolean isRememberAccAndPwd();

        /** 提示账号或密码为空 */
        void showEmptyPrompt();

        /** 登陆操作结束 */
        void onLoginFinished(User user);

    }

    interface Presenter extends IBasePresenter {

        void onCreate();
        void onDestroy();

        /** 登陆 */
        void login();

    }

}
