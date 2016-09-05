package com.evan.demo.model.net.restapi;

import com.evan.demo.model.bean.WeatherData;
import com.evan.demo.model.bean.WeatherListData;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by evanyu on 16/6/14.
 */
public interface WeatherService {

    // 获取实时天气数据
    @POST("/")
    @FormUrlEncoded
    Observable<WeatherData> getWeatherData(@Field("app") String app,
                                           @Field("weaid") String weaid,
                                           @Field("appkey") String appkey,
                                           @Field("sign") String sign,
                                           @Field("format") String format);

    // 获取多天天气数据
    @POST("/")
    @FormUrlEncoded
    Observable<WeatherListData> getMultiDaysWeatherData(@FieldMap Map<String, String> params);

}
