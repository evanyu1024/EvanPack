package com.evan.demo.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.evan.demo.manager.BaseApp;

/**
 * Toast工具类
 * Created by evanyu on 16/6/3.
 */
public class ToastUtils {

    private static Toast mToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    // 私有化构造方法
    private ToastUtils() {
        // empty
    }

    public static void showToast(String text) {
        showToastShort(text);
    }

    public static void showToastShort(String text) {
        showToastSafe(text, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(String text) {
        showToastSafe(text, Toast.LENGTH_LONG);
    }

    // 保证在UI线程中显示,并且在显示Toast的时候不能再试图显示下一个Toast
    private static void showToastSafe(final String text, final int duration) {
        // 判断是否是UI线程
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // 主线程
            showToast(text, duration);
        } else {
            // 子线程
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    showToast(text, duration);
                }
            });
        }
    }

    private static void showToast(String text, int duration) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(BaseApp.getInstance(), text, duration);
        mToast.show();
    }

}
