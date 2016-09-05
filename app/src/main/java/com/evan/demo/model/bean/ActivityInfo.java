package com.evan.demo.model.bean;

/**
 *
 * Created by evanyu on 16/8/31.
 */
public class ActivityInfo {

    public Class<?> clazz;
    public String title;

    public ActivityInfo(Class<?> clazz, String title) {
        this.clazz = clazz;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
