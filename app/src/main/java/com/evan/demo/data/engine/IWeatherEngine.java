package com.evan.demo.data.engine;

import com.evan.demo.data.bean.Weather;

/**
 * Created by evanyu on 16/6/14.
 */
public interface IWeatherEngine extends IBaseEngine {

    /**
     * 获取实时天气信息
     * @param weaid 城市id
     * @return 天气对象
     */
    void getWeatherData(String weaid, RequestListener<Weather> listener);

}
