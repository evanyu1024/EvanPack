package com.evan.demo.ui;

/**
 * View层的顶层接口
 * Created by evanyu on 16/7/14.
 */
public interface IBaseView {

    /** 显示toast */
    void showToast(String msg);

    /** 显示进度提示 */
    void showLoading();

    /** 隐藏进度提示 */
    void hideLoading();

    /** 销毁当前View */
    void finishView();

}
