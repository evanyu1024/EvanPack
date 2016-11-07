package com.evan.demo.manager;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.view.ViewConfiguration;

import com.evan.demo.utils.CrashHandler;

import java.lang.reflect.Field;

/**
 * BaseApplication
 * Created by evanyu on 16/6/4.
 */
public class BaseApp extends Application {

    private static BaseApp sInstance;
    public static Thread sMainThread = Thread.currentThread();

    public static Handler sMainThreadHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initCrashHandler();

        // 强制显示actionbar中的overflow
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }
    }

    public static BaseApp getInstance() {
        return sInstance;
    }

    private void initCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Thread getMainThread() {
        return sMainThread;
    }

    public static Handler getMainThreadHandler() {
        return sMainThreadHandler;
    }
}
