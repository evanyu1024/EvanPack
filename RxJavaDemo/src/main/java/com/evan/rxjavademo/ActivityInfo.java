package com.evan.rxjavademo;

import android.app.Activity;

/**
 * Created by evanyu on 16/8/2.
 */
public class ActivityInfo {

    public Class<? extends Activity> clazz;
    public String title;

    public ActivityInfo(Class<? extends Activity> activity, String title) {
        this.clazz = activity;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
