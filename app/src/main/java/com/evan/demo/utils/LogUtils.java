package com.evan.demo.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * 日志输出工具类
 * Created by evanyu on 16/6/2.
 */
public class LogUtils {

    /** 日志输出时的TAG */
    private static String TAG = "mtag";

    /** 日志输出级别V */
    public static final int LEVEL_VERBOSE = 6;

    /** 日志输出级别D */
    public static final int LEVEL_DEBUG = 5;

    /** 日志输出级别I */
    public static final int LEVEL_INFO = 4;

    /** 日志输出级别W */
    public static final int LEVEL_WARN = 3;

    /** 日志输出级别E */
    public static final int LEVEL_ERROR = 2;

    /** 日志输出级别A */
    public static final int LEVEL_ASSERT = 1;

    /** 是否是调试模式(是否输出LOG) */
    public static boolean isDebug = true;

    /** 当前输出LOG的最低级别 */
    public static int minLevel = LEVEL_INFO;

    /** 是否输出日志 */
    public static boolean isPrintLog(String msg, int level) {
        if (!isDebug) return false;
        if (level < minLevel) return false;
        if (TextUtils.isEmpty(msg)) return false;
        return true;
    }

    /** 输出verbose级别的LOG */
    public static void v(String msg) {
        if (isPrintLog(msg, LEVEL_VERBOSE)) {
            Log.v(TAG, buildMessage(msg));
        }
    }

    /** 输出debug级别的LOG */
    public static void d(String msg) {
        if (isPrintLog(msg, LEVEL_DEBUG)) {
            Log.d(TAG, buildMessage(msg));
        }
    }

    /** 输出info级别的LOG */
    public static void i(String msg) {
        if (isPrintLog(msg, LEVEL_INFO)) {
            Log.i(TAG, buildMessage(msg));
        }
    }

    /** 输出warn级别的LOG */
    public static void w(String msg) {
        if (isPrintLog(msg, LEVEL_WARN)) {
            Log.w(TAG, buildMessage(msg));
        }
    }

    /** 输出debug级别的LOG */
    public static void e(String msg) {
        if (isPrintLog(msg, LEVEL_ERROR)) {
            Log.e(TAG, buildMessage(msg));
        }
    }

    /** 输出verbose级别的LOG和Throwable */
    public static void v(String msg, Throwable throwable) {
        if (isPrintLog(msg, LEVEL_VERBOSE)) {
            Log.v(TAG, buildMessage(msg), throwable);
        }
    }

    /** 输出debug级别的LOG和Throwable */
    public static void d(String msg, Throwable throwable) {
        if (isPrintLog(msg, LEVEL_DEBUG)) {
            Log.d(TAG, buildMessage(msg), throwable);
        }
    }

    /** 输出info级别的LOG和Throwable */
    public static void i(String msg, Throwable throwable) {
        if (isPrintLog(msg, LEVEL_INFO)) {
            Log.i(TAG, buildMessage(msg), throwable);
        }
    }

    /** 输出warn级别的LOG和Throwable */
    public static void w(String msg, Throwable throwable) {
        if (isPrintLog(msg, LEVEL_WARN)) {
            Log.w(TAG, buildMessage(msg), throwable);
        }
    }

    /** 输出error级别的LOG和Throwable */
    public static void e(String msg, Throwable throwable) {
        if (isPrintLog(msg, LEVEL_ERROR)) {
            Log.e(TAG, buildMessage(msg), throwable);
        }
    }

    /** 获取带有method名称的message */
    private static String buildMessage(String msg) {
        try {
            StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
            String caller = "<unknown>";
            for (int i = 2; i < trace.length; i++) {
                Class<?> clazz = trace[i].getClass();
                if (!clazz.equals(LogUtils.class)) {
                    String callingClass = trace[i].getClassName();
                    callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                    callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                    caller = callingClass + "." + trace[i].getMethodName();
                    break;
                }
            }
            return String.format(Locale.CHINA, "[Thread ID : %d ] %s: %s",
                    Thread.currentThread().getId(), caller, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
