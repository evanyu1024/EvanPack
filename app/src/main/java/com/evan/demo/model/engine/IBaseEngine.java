package com.evan.demo.model.engine;

/**
 * Created by evanyu on 16/6/14.
 */
public interface IBaseEngine {

    /*void addSubscriber(Subscriber<?> subscriber);

    void removeSubscriber(Subscriber<?> subscriber);*/

    /** 取消所有事件的订阅 */
    void unsubscribeAll();

}
