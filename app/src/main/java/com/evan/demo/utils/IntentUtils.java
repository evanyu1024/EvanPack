package com.evan.demo.utils;

import android.content.Context;
import android.content.Intent;

import com.evan.demo.ui.activity.MainActivity;

/**
 * Intent工具类
 * Created by evanyu on 16/8/3.
 */
public class IntentUtils {

    private IntentUtils() {
    }

    public static void startActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
