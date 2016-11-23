package com.evan.activityviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
    }

    public void openView(View v) {
        startActivity(new Intent(this, Activity01.class));
    }

    public void testAnimation(View v) {
        Animation ani = new TranslateAnimation(view.getWidth(), 0, 0, 0);
        ani.setDuration(10000);
        ani.setFillAfter(true);
        ani.setRepeatCount(0);
        view.startAnimation(ani);
    }

}
