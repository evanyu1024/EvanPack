package com.evan.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.evan.demo.manager.BaseApp;

/**
 * SharedPreferences工具类
 * Created by evanyu on 16/7/12.
 */
public class SPUtils {

    private static final String DEFAULT_SP_NAME = "config";

    private SPUtils() {}

    private static Context getContext() {
        return BaseApp.getInstance();
    }

    public static boolean put(String key, String value, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().putString(key, value).commit();
    }

    public static boolean put(String key, int value, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().putInt(key, value).commit();
    }

    public static boolean put(String key, float value, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().putFloat(key, value).commit();
    }

    public static boolean put(String key, long value, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().putLong(key, value).commit();
    }

    public static boolean put(String key, boolean value, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().putBoolean(key, value).commit();
    }

    public static String get(String key, String defValue, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static int get(String key, int defValue, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static float get(String key, float defValue, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static long get(String key, long defValue, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static boolean get(String key, boolean defValue, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /*************************************************************************************************/

    public static boolean put(String key, String value) {
        return put(key, value, DEFAULT_SP_NAME);
    }

    public static boolean put(String key, int value) {
        return put(key, value, DEFAULT_SP_NAME);
    }

    public static boolean put(String key, float value) {
        return put(key, value, DEFAULT_SP_NAME);
    }

    public static boolean put(String key, long value) {
        return put(key, value, DEFAULT_SP_NAME);
    }

    public static boolean put(String key, boolean value) {
        return put(key, value, DEFAULT_SP_NAME);
    }

    public static String get(String key, String defValue) {
        return get(key, defValue, DEFAULT_SP_NAME);
    }

    public static int get(String key, int defValue) {
        return get(key, defValue, DEFAULT_SP_NAME);
    }

    public static float get(String key, float defValue) {
        return get(key, defValue, DEFAULT_SP_NAME);
    }

    public static long get(String key, long defValue) {
        return get(key, defValue, DEFAULT_SP_NAME);
    }

    public static boolean get(String key, boolean defValue) {
        return get(key, defValue, DEFAULT_SP_NAME);
    }

    /*************************************************************************************************/

    public static boolean remove(String key, String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().remove(key).commit();
    }

    public static boolean remove(String key) {
        SharedPreferences sp = getContext().getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.edit().remove(key).commit();
    }

    public static boolean clear(String spName) {
        SharedPreferences sp = getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

    public static boolean clear() {
        SharedPreferences sp = getContext().getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

}
