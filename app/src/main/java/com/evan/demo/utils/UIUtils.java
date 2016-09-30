package com.evan.demo.utils;

import com.evan.demo.manager.application.BaseApp;

public class UIUtils {

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = BaseApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = BaseApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}

