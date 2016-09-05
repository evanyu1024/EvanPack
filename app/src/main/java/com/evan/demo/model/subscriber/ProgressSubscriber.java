package com.evan.demo.model.subscriber;

import com.evan.demo.utils.ActivityCollector;
import com.evan.demo.utils.LogUtils;
import com.evan.demo.ui.activity.BaseActivity;

import rx.Subscriber;

/**
 * Created by evanyu on 16/8/1.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        // LogUtils.d("onStart..." + Thread.currentThread());
        showLoading();
    }

    @Override
    public void onCompleted() {
        hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        hideLoading();
        LogUtils.d(e.toString());
    }

    private void showLoading() {
        BaseActivity foregoundActivity = ActivityCollector.getForegroundActivity();
        if (foregoundActivity != null) {
            foregoundActivity.showLoading();
        }
    }

    private void hideLoading() {
        BaseActivity foregoundActivity = ActivityCollector.getForegroundActivity();
        if (foregoundActivity != null) {
            foregoundActivity.hideLoading();
        }
    }

}
