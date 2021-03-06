package com.evan.demo.model.engine.impl;

import android.os.Handler;
import android.os.SystemClock;

import com.evan.demo.model.engine.IBaseEngine;
import com.evan.demo.model.engine.IUserEngine;

/**
 * Created by evanyu on 16/5/30.
 */
public class UserEngineImpl implements IUserEngine, IBaseEngine {

    private Handler mHandler = new Handler();

    @Override
    public void login(final String name, final String pwd, final OnLoginListener listener) {
        // 模式登陆操作是需要网络请求的耗时操作,在子线程中执行
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                if ("admin".equals(name) && "123".equals(pwd)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                // 返回原始数据
                                listener.loginSuccess(name + "," + pwd);
                            }
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.loginFailed();
                            }
                        }
                    });
                }
            }
        }.start();
    }

    @Override
    public void unsubscribeAll() {
    }
}
