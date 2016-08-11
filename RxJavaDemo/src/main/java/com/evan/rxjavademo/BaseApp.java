package com.evan.rxjavademo;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by evanyu on 16/8/2.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("mtag");
    }
}
