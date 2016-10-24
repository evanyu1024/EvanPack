package com.evan.demo.ui.iview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * View层的顶层接口
 * Created by evanyu on 16/9/8.
 */
public interface IBaseView {

    /** 获取上下文 */
    Context getContext();

    /** 获取来自上一个页面的意图对象 */
    Intent getIntent();

    /** 显示Toast */
    void showToast(String text);

    /** 显示Toast */
    void showToastLong(String text);

    /** 显示进度提示 */
    void showLoading();

    /** 隐藏进度提示 */
    void hideLoading();

    /** 是否正在加载 */
    boolean isLoading();

    /** 启动Activity */
    void startActivity(Class<? extends Activity> cls);

    /** 启动Activity */
    void startActivity(Intent intent);

    /** 启动具有返回结果的Activity */
    void startActivityForResult(Intent intent, int requestCode);

    /** 关闭Activity */
    void finishActivity();

}
