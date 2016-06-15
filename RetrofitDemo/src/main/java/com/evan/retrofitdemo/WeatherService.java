package com.evan.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by evanyu on 16/6/1.
 */
public interface WeatherService {

    @GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
    Call<ResponseBody> getWeatherData();

    /*@GET("/") // ("?app=weather.today&weaid={weaid}&&appkey={appkey}&sign={sign}&format=json")
    Call<ResponseBody> getWeatherData(@Query("app") String app,
                                      @Query("weaid") String weaid,
                                      @Query("appkey") String appkey,
                                      @Query("sign") String sign,
                                      @Query("format") String format);*/

    @POST("/")
    Call<ResponseBody> getWeatherData(@Field("app") String app,
                                      @Field("weaid") String weaid,
                                      @Field("appkey") String appkey,
                                      @Field("sign") String sign,
                                      @Field("format") String format);


//    @GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
//    Call<RequestBody> getWearherData();

}
