package com.evan.demo.manager.subscriber;

import com.evan.demo.model.bean.response.base.HttpResponse;
import com.evan.demo.model.exception.ApiException;
import com.evan.demo.utils.LogUtils;
import com.evan.demo.utils.ToastUtils;

import rx.Subscriber;

/**
 * Subscriber默认子类
 * Created by evanyu on 16/9/8.
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public abstract void onNext(T t);

    @Override
    public void onCompleted() {
        LogUtils.d("......onCompleted()......");
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.d("......onError()......");
        e.printStackTrace();
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            // 未在登录状态
            HttpResponse response = apiException.getErrorResponse();
            if (response.isSessionOff()) {
                // todo 重新登录
                LogUtils.d("未在登录状态,需要重新登录....");
            } else {
                LogUtils.d("其他网络错误");
                ToastUtils.showToast("服务器异常,请稍后再试");
            }
        } else {
            ToastUtils.showToast("访问失败,请检查网络是否畅通");
        }
    }

}
