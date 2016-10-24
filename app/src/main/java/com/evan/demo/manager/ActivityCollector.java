package com.evan.demo.manager;

import android.app.Activity;

import com.evan.demo.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity的统一管理工具
 * Created by evanyu on 16/6/12.
 */
public class ActivityCollector {

    private static List<Activity> sList = new ArrayList<>();

    private static BaseActivity sForegroundActivity;

    // 私有化构造方法
    private ActivityCollector() {
    }

    public static void addActivity(Activity activity) {
        sList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 设置前台Activity
     */
    public static void setForefroundActivity(BaseActivity activity) {
        sForegroundActivity = activity;
    }

    /**
     * 获取前台Activity
     * @return
     */
    public static BaseActivity getForegroundActivity() {
        return sForegroundActivity;
    }

}
