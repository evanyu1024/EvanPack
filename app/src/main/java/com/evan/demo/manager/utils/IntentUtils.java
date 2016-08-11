package com.evan.demo.manager.utils;

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

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
