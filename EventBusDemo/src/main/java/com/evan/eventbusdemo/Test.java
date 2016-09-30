package com.evan.eventbusdemo;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by evanyu on 16/9/8.
 */
public class Test {

    public Test() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(String event) {
        Log.d("mtag", "Test...onEvent..." + Thread.currentThread().getName() + "..." + event);
    }

}
