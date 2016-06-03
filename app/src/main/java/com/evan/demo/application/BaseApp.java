package com.evan.demo.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by evanyu on 16/6/4.
 */
public class BaseApp extends Application {

    private static Context mContext;
    public static Thread mMainThread = Thread.currentThread();

    public static Handler mMainThreadHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
}
