package com.evan.activityviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by evanyu on 16/11/21.
 */

public class Activity01 extends AppCompatActivity {

    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mtag", "onCreate...");
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity01);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mtag", "onResume...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("mtag", "onStart...");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("mtag", "onWindowFocusChanged..." + hasFocus);
        if (hasFocus) {
            Animation ani = new TranslateAnimation(0, 0, -view1.getMeasuredHeight(), 0);
            ani.setDuration(1000);
            ani.setFillAfter(true);
            ani.setRepeatCount(0);
            view1.startAnimation(ani);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mtag", "onPause...");
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mtag", "onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mtag", "onDestroy...");
    }
}
