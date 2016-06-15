package com.evan.demo.data.engine.impl;

import com.evan.demo.data.bean.Weather;
import com.evan.demo.data.engine.IWeatherEngine;
import com.evan.demo.data.net.NetConstant;
import com.evan.demo.data.net.service.WeatherService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by evanyu on 16/6/14.
 */
public class WeatherEngineImpl implements IWeatherEngine {

    private WeatherService mWeatherService;

    public WeatherEngineImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NetConstant.BASE_URL).build();
        mWeatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public void getWeatherData(String weaid, final RequestListener<Weather> listener) {
        Call<ResponseBody> call = mWeatherService.getWeatherData("weather.today", weaid, NetConstant.APP_KEY, NetConstant.SIGN, "json");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Weather weather = new Gson().fromJson(response.body().string(), Weather.class);
                    listener.onResponse(weather);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(call, t);
            }
        });
    }
}
