package com.evan.demo.ui;

/**
 * View层的顶层接口
 * Created by evanyu on 16/7/14.
 */
public interface IBaseView {

    /** 显示进度提示 */
    void showLoading();

    /** 银行进度提示 */
    void hideLoading();

}
