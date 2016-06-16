package com.evan.retrofitdemo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by evanyu on 16/6/1.
 */
public interface MyApiService {

    /*@GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
    Call<ResponseBody> getWeatherData();*/

    @GET("/?") // ("?app=weather.today&weaid={weaid}&&appkey={appkey}&sign={sign}&format=json")
    Call<ResponseBody> getWeatherData(@Query("app") String app,
                                      @Query("weaid") int weaid,
                                      @Query("appkey") String appkey,
                                      @Query("sign") String sign,
                                      @Query("format") String format);

    @GET("/?")
    Call<Weather> getWeatherData(@QueryMap Map<String, Object> map);

    /*@POST("/")
    Call<ResponseBody> getWeatherData(@Field("app") String app,
                                      @Field("weaid") int weaid,
                                      @Field("appkey") String appkey,
                                      @Field("sign") String sign,
                                      @Field("format") String format);*/

    @GET
    Call<ResponseBody> downloadImage(@Url String url);

//    @GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
//    Call<RequestBody> getWearherData();

}
