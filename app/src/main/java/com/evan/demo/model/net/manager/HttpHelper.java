package com.evan.demo.model.net.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络访问帮助类
 * Created by evanyu on 16/9/12.
 */
public class HttpHelper {

    private static final int TIMEOUT = 10000; // 超时时间

    public static byte[] doGet(String urlPath) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setUseCaches(false);
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                int len = 0;
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                return baos.toByteArray();
            } else {
                throw new RuntimeException("网络访问失败:" + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    public static Bitmap loadImage(String urlPath) {
        byte[] result = doGet(urlPath);
        if (result != null) {
            return BitmapFactory.decodeByteArray(result, 0, result.length);
        } else {
            return null;
        }
    }

}
