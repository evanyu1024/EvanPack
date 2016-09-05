package com.evan.test;

import android.os.Environment;

/**
 * 存储设备管理工具类
 * Created by evanyu on 16/8/31.
 */
public class StorageUtils {

    private StorageUtils() {
        // empty
    }

    /**
     * 外置存储是否已挂载
     */
    public static boolean isExternalStorageMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取外置存储路径
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

}
