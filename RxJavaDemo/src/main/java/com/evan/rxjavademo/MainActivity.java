package com.evan.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.init("mtag");
    }

    public void test1(View view) {
        Observable.just("aaa","bbb","ccc")
            .subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Logger.d("error!!!");
                }

                @Override
                public void onNext(String s) {
                    int i = 1/0;
                    Logger.d(s);
                }
            });
    }

}
