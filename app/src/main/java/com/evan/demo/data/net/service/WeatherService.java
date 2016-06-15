package com.evan.demo.data.net.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by evanyu on 16/6/14.
 */
public interface WeatherService {

    // 获取实时天气数据
    @POST("/")
    Call<ResponseBody> getWeatherData(@Field("app") String app,
                                      @Field("weaid") String weaid,
                                      @Field("appkey") String appkey,
                                      @Field("sign") String sign,
                                      @Field("format") String format);

}
