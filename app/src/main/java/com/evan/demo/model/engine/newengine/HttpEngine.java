package com.evan.demo.model.engine.newengine;

import com.evan.demo.model.constant.NetConstant;
import com.evan.demo.model.net.manager.RetrofitFactory;
import com.evan.demo.model.net.restapi.ApiService;

import retrofit2.Retrofit;

/**
 * 网络访问业务类
 * Created by evanyu on 16/9/12.
 */
public class HttpEngine extends BaseEngine {

    private ApiService mApiService;

    public HttpEngine() {
        Retrofit retrofit = RetrofitFactory.createDefaultRetrofit(NetConstant.BASE_URL);
        mApiService = retrofit.create(ApiService.class);
    }

    /**
     * 登录
     *
     * @param name 用户名
     * @param pwd  密码
     */
//    public void login(String name, String pwd, Subscriber<HttpResponse> subscriber) {
//        Observable<HttpResponse> observable = mApiService.login(name, MD5Utils.getMD5(pwd));
//        doDefaultSubscribe(subscriber, observable);
//    }

}