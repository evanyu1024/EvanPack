package com.evan.demo.ui.iview;

/**
 * Created by evanyu on 16/7/14.
 */
public interface ILoginView extends IBaseView {

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

    /** 判断是需要否记住密码 */
    boolean isRememberAccAndPwd();

    /** 提示帐号或密码为空 */
    void showEmptyError();

    /** 登陆成功 */
    void onLoginSuccess();

    /** 登陆失败 */
    void onLoginFailed();

}
