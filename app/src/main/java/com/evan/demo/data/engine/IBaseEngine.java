package com.evan.demo.data.engine;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by evanyu on 16/6/14.
 */
public interface IBaseEngine {

    interface RequestListener<T> {
        void onResponse(T data);
        void onFailure(Call<ResponseBody> call, Throwable t);
    }

}
