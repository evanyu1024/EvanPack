package com.evan.demo.model.engine.newengine;

import com.evan.demo.model.bean.response.base.HttpResponse;
import com.evan.demo.model.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 业务基类
 * Created by evanyu on 16/9/12.
 */
public class BaseEngine {

    private CompositeSubscription mCompositeSubscription; // 用于统一管理subscriber

    public BaseEngine() {
        mCompositeSubscription = new CompositeSubscription();
    }

    /**
     * 执行默认的订阅操作
     * ps:主线程订阅,子线程执行,主线程响应
     */
    protected void doDefaultSubscribe(Subscriber<? extends HttpResponse> subscriber, Observable<? extends HttpResponse> observable) {
        addSubscriber(subscriber);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<HttpResponse, HttpResponse>() {
                    @Override
                    public HttpResponse call(HttpResponse httpResponse) {
                        if (httpResponse.isError() || httpResponse.isSessionOff()) {
                            throw new ApiException(httpResponse);
                        } else {
                            return httpResponse;
                        }
                    }
                })
                .subscribe((Subscriber) subscriber);
    }

    /**
     * 添加订阅
     */
    protected void addSubscriber(Subscriber<? extends HttpResponse> subscriber) {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.add(subscriber);
        }
    }

    /**
     * 取消当前业务中所有订阅
     */
    public void cancelAll() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

}
