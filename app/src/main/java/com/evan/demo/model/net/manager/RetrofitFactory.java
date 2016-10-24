package com.evan.demo.model.net.manager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工厂类
 * Created by evanyu on 16/9/16.
 */
public class RetrofitFactory {

    private RetrofitFactory() {
    }

    public static Retrofit createDefaultRetrofit(String baseUrl) {
        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(OkHttpFractory.createDefaultOkHttpClient())
                .build();
    }

}
