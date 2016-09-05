package com.evan.demo.ui.iview;

import android.content.Context;

/**
 * View层的顶层接口
 * Created by evanyu on 16/7/14.
 */
public interface IBaseView {

    /** 获取上下文环境 */
    Context getContext();

    /** 显示toast */
    void showMessage(String msg);

    /** 显示进度提示 */
    void showLoading();

    /** 隐藏进度提示 */
    void hideLoading();

    /** 销毁当前View */
    void finishView();

}
