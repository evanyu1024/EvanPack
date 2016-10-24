package com.evan.demo.model.engine.impl;

import com.evan.demo.model.bean.WeatherData;
import com.evan.demo.model.bean.WeatherListData;
import com.evan.demo.model.constant.NetConstant;
import com.evan.demo.model.engine.IWeatherEngine;
import com.evan.demo.model.net.manager.OkHttpFractory;
import com.evan.demo.model.net.restapi.WeatherService;
import com.evan.demo.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by evanyu on 16/6/14.
 */
public class WeatherEngineImpl implements IWeatherEngine {

    private WeatherService mWeatherService;
    private CompositeSubscription mCompositeSubscription;

    public WeatherEngineImpl() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConstant.BASE_URL)
                .client(OkHttpFractory.createDefaultOkHttpClient())
                .build();
        mWeatherService = retrofit.create(WeatherService.class);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void getWeatherData(String weaid, Subscriber<WeatherData> subscriber) {
        mCompositeSubscription.add(subscriber);
        Observable<WeatherData> observable = mWeatherService.getWeatherData("weather.today", weaid, NetConstant.APP_KEY, NetConstant.SIGN, "json");
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        LogUtils.d("doOnNext...");
                    }
                })
                .subscribe(subscriber);
    }

    // http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
    @Override
    public void getMultiDaysWeatherData(String weaid, Subscriber<WeatherListData> subscriber) {
        mCompositeSubscription.add(subscriber);
        Map<String, String> map = new HashMap();
        map.put("app", "weather.future");
        map.put("weaid", weaid);
        map.put("appkey", NetConstant.APP_KEY);
        map.put("sign", NetConstant.SIGN);
        map.put("format", "json");
        Observable<WeatherListData> observable = mWeatherService.getMultiDaysWeatherData(map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    @Override
    public void getAllWeatherData(String weaid, Subscriber<Object> subscriber) {
        mCompositeSubscription.add(subscriber);
        Observable<WeatherData> observable1 = mWeatherService.getWeatherData("weather.today", weaid, NetConstant.APP_KEY, NetConstant.SIGN, "json");
        Map<String, String> map = new HashMap();
        map.put("app", "weather.future");
        map.put("weaid", weaid);
        map.put("appkey", NetConstant.APP_KEY);
        map.put("sign", NetConstant.SIGN);
        map.put("format", "json");
        Observable<WeatherListData> observable2 = mWeatherService.getMultiDaysWeatherData(map);

        Observable<Object> observable = Observable.merge(observable2, observable1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    @Override
    public void unsubscribeAll() {
        if(mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
