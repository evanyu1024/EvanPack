package com.evan.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by evanyu on 16/6/1.
 */
public interface WeatherService {

    @GET("?app=weather.today&weaid=1&&appkey=12785&sign=af1413e4a572b4e9732a6aae4d154920&format=json")
    Call<ResponseBody> getWeatherData();

}
