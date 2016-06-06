package com.evan.imageloader;

import android.app.Activity;

/**
 * Created by evanyu on 16/6/6.
 */
public class ActivityInfo {

    public String name;
    public Class<?> clazz;

    public ActivityInfo(String name, Class<? extends Activity> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return name;
    }
}
