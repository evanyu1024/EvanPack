package com.evan.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.evan.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    User user;
    ActivityMainBinding dataBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        dataBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinder.setUser(user = new User("Evan", 18));
    }

    public void changeTv1(View view) {
        user.setName("test!!!");
        // dataBinder.setUser(user);
        // dataBinder.notifyChange();
        dataBinder.invalidateAll();
    }

    public void changeTv2(View view) {
        dataBinder.setName2("哈哈");
    }

    public void changeTv3(View view) {
    }
}
