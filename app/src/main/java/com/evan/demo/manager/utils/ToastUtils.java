package com.evan.demo.manager.utils;

import android.os.Looper;
import android.widget.Toast;

import com.evan.demo.manager.application.BaseApp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Toast工具类
 * Created by evanyu on 16/6/3.
 */
public class ToastUtils {

    private static boolean canShowToast = true; // 控制是否能够显示Toast
    private static Toast mToast;
    private static final ScheduledExecutorService SHOW_TOAST_EXECUTOR = Executors.newSingleThreadScheduledExecutor();

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
            BaseApp.getMainThreadHandler().post(new Runnable() {
                @Override
                public void run() {
                    showToast(text, duration);
                }
            });
        }
    }

    private static void showToast(String text, int duration) {
        if (!canShowToast) return;
        canShowToast = false;
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.getContext(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.setDuration(duration);
        mToast.show();
        final long delay = duration == Toast.LENGTH_LONG ? 3500 : 2000;
        SHOW_TOAST_EXECUTOR.schedule(new Runnable() {
            @Override
            public void run() {
                canShowToast = true;
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

}
