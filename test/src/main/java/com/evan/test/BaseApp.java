package com.evan.test;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by evanyu on 16/8/29.
 */
public class BaseApp extends Application {

    private static Handler sHandler = new Handler();
    private static BaseApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Handler getMainThreadHandler() {
        return sHandler;
    }

    public static Context getContext() {
        return sInstance.getApplicationContext();
    }

}
