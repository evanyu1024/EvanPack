package com.evan.retrofitdemo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
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

    /**
     * 作用:下载大文件
     * 默认情况下,Retrofit在处理结果前会将服务端的Response全部读进内存
     * 如果服务端返回的是一个非常大的文件,则容易产生OOM
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadBigFile(@Url String url);

//    @GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
//    Call<RequestBody> getWearherData();

}
