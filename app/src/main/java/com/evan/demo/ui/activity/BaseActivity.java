package com.evan.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.evan.demo.utils.manage.ActivityCollector;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by evanyu on 16/6/8.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Activity mActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 统计页面跳转
        // PS: 需保证在 MobclickAgent.onResume 前调用,因为会在 onResume 中保存信息
        MobclickAgent.onPageStart(getClass().getSimpleName());
        // 统计页面时长
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    // 空实现
    @Override
    public void onClick(View v) {
        // empty
    }
}
