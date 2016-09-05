package com.evan.demo.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
     * 获取外置存储的绝对路径
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取外置存储的剩余空间大小(单位:字节)
     */
    public static long getExternalStorageAvailableSize() {
        StatFs stat = new StatFs(getExternalStoragePath());
        long availableBlocks = stat.getAvailableBlocks();
        long blockSize = stat.getBlockSize();
        return availableBlocks * blockSize;
    }

    /**
     * 获取外置存储的总大小(单位:字节)
     */
    public static long getExternalStorageTotalSize() {
        StatFs stat = new StatFs(getExternalStoragePath());
        long totalBlocks = stat.getBlockCount();
        long blockSize = stat.getBlockSize();
        return totalBlocks * blockSize;
    }

    /**
     * 读取文件数据
     *
     * @param file 文件的位置
     */
    public static byte[] readData(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        FileInputStream fis = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            fis = new FileInputStream(file);
            byte[] buf = new byte[8192];
            int len = -1;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
