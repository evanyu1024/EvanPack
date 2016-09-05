package com.evan.demo.manager.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.evan.demo.utils.CrashHandler;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by evanyu on 16/6/4.
 */
public class BaseApp extends Application {

    private static Context sContext;
    public static Thread sMainThread = Thread.currentThread();

    public static Handler sMainThreadHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        initUMeng();
        initCrashHandler();
    }

    private void initCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    private void initUMeng() {
        // 禁止默认的页面统计方式(默认只会统计Activity的页面跳转)
        MobclickAgent.openActivityDurationTrack(false);

        // 设置是否对日志信息进行加密, 默认false(不加密)
        // AnalyticsConfig.enableEncrypt(boolean enable);//6.0.0版本以前
        // MobclickAgent.enableEncrypt(true);//6.0.0版本及以后
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public static Thread getMainThread() {
        return sMainThread;
    }

    public static Handler getMainThreadHandler() {
        return sMainThreadHandler;
    }
}
