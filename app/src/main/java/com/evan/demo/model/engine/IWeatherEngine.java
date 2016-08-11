package com.evan.demo.model.engine;

import com.evan.demo.model.domain.bean.WeatherData;
import com.evan.demo.model.domain.bean.WeatherListData;

import rx.Subscriber;

/**
 * Created by evanyu on 16/6/14.
 */
public interface IWeatherEngine extends IBaseEngine {

    /**
     * 获取实时天气信息
     * @param weaid 城市id
     * @return 天气对象
     */
    void getWeatherData(String weaid, Subscriber<WeatherData> subscriber);

    /**
     * 获取多天天气信息
     * @param weaid 城市id
     * @return 天气集合
     */
    void getMultiDaysWeatherData(String weaid, Subscriber<WeatherListData> subscriber);

    /**
     * 测试专用,同时访问两个接口
     * @param weaid
     * @param subscriber
     */
    void getAllWeatherData(String weaid, Subscriber<Object> subscriber);
}

