package com.evan.demo.utils.manage;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity的统一管理工具
 * Created by evanyu on 16/6/12.
 */
public class ActivityCollector {

    private static List<Activity> list = new ArrayList<>();

    // 私有化构造方法
    private ActivityCollector() {
    }

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
