package com.evan.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Test t = new Test();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_post_sticky).setOnClickListener(this);

        EventBus.getDefault().register(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                EventBus.getDefault().post("普通事件");
                break;
            case R.id.btn_post_sticky:
                EventBus.getDefault().postSticky("粘性事件");
                break;
            default:
                break;
        }
    }

    public void openSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void testEventBus(String event) {
        Log.d("mtag", "MainActivity...testEventBus..." + Thread.currentThread().getName() + "..." + event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
