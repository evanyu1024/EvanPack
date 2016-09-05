package com.evan.demo.model.net;

import com.evan.demo.manager.application.BaseApp;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by evanyu on 16/8/1.
 */
public class OkHttpFractory {

    private static OkHttpClient sOkHttpClinet;

    private OkHttpFractory() {
    }

    public static OkHttpClient getOkHttpClient() {
        synchronized (OkHttpFractory.class) {
            if (sOkHttpClinet == null) {
                synchronized (OkHttpFractory.class) {
                    int cacheSize = 10 * 1024 * 1024; // 10 MiB
                    Cache cache = new Cache(BaseApp.getContext().getCacheDir(), cacheSize);
                    sOkHttpClinet = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS) // 设置超时时间
                            .readTimeout(10, TimeUnit.SECONDS) // 设置读取超时时间
                            .writeTimeout(10, TimeUnit.SECONDS) // 设置写入超时时间
                            .cache(cache)
                            .build();
                }
            }
        }
        return sOkHttpClinet;
    }

}
