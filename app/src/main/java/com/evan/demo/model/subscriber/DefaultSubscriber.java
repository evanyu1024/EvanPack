package com.evan.demo.model.subscriber;

import rx.Subscriber;

/**
 * Created by evanyu on 16/7/28.
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // empty
    }

    @Override
    public void onError(Throwable e) {
        // empty
    }

    @Override
    public abstract void onNext(T t);

}
