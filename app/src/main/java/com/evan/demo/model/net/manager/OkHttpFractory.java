package com.evan.demo.model.net.manager;


import com.evan.demo.manager.AppConfig;
import com.evan.demo.manager.BaseApp;
import com.evan.demo.utils.LogUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClient工厂类
 * Created by evanyu on 16/8/1.
 */
public class OkHttpFractory {

    // 单位:秒
    private static final int DEFAULT_TIMEOUT = 10; // 默认超时时间(单位:秒)
    private static final int CONNECT_TIMEOUT = DEFAULT_TIMEOUT; // 连接超时时间
    private static final int READ_TIMEOUT = DEFAULT_TIMEOUT; // 读取超时时间
    private static final int WRITE_TIMEOUT = DEFAULT_TIMEOUT; // 写入超时时间

    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 缓存大小,10MB

    // 单例对象
    private volatile static OkHttpClient sDefaultOkHttpClinet;

    private OkHttpFractory() {
    }

    public static OkHttpClient createDefaultOkHttpClient() {
        if (sDefaultOkHttpClinet == null) {
            synchronized (OkHttpFractory.class) {
                if (sDefaultOkHttpClinet == null) {
                    // 缓存
                    Cache cache = new Cache(BaseApp.getInstance().getCacheDir(), CACHE_SIZE);
                    // OkHttp构建器
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) // 设置连接超时时间
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) // 设置读取超时时间
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) // 设置写入超时时间
                            .cache(cache) // 缓存
                            .retryOnConnectionFailure(true) // 错误重连
                            // Cookie处理
                            .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApp.getInstance())));
                    // 插值器
                    if (AppConfig.debug) {
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                LogUtils.d(message);
                            }
                        });
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(logging); // 插值器
                    }
                    // 创建对象
                    sDefaultOkHttpClinet = builder.build();
                }
            }
        }
        return sDefaultOkHttpClinet;
    }

}
