package com.evan.demo.ui.iview;

/**
 * Created by evanyu on 16/7/14.
 */
public interface ILoginView extends IBaseView {

    /** 登陆成功 */
    void onLoginSuccess();

    /** 登陆失败 */
    void onLoginFailed();

}
