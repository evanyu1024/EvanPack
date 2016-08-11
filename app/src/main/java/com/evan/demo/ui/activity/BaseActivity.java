package com.evan.demo.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.manager.utils.ActivityCollector;
import com.evan.demo.manager.utils.ToastUtils;
import com.evan.demo.ui.IBaseView;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by evanyu on 16/6/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, IBaseView {

    protected BaseActivity mActivity = this;
    private FrameLayout mBaseContentContainer; // 主容器
    private View mLoadingView; // 进度提示View

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        onCreate();
    }

    /**
     * 初始化方法
     */
    protected void onCreate() {
        // empty
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.base_content_container);
        mBaseContentContainer = (FrameLayout) findViewById(R.id.base_content_container);
        getLayoutInflater().inflate(layoutResID, mBaseContentContainer, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 统计页面跳转
        // PS: 需保证在 MobclickAgent.onResume 前调用,因为会在 onResume 中保存信息
        MobclickAgent.onPageStart(getClass().getSimpleName());
        // 统计页面时长
        MobclickAgent.onResume(this);
        // 当前Activity获取焦点后设置为前台Activity
        ActivityCollector.setForefroundActivity(mActivity);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onPause(this);
        ActivityCollector.setForefroundActivity(null);
    }

    // 空实现
    @Override
    public void onClick(View v) {
        // empty
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void showLoading() {
        if (mBaseContentContainer != null && mLoadingView == null) {
            View view = getLayoutInflater().inflate(R.layout.common_progress, mBaseContentContainer, true);
            mLoadingView = view.findViewById(R.id.progress_container);
            ImageView ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
            AnimationDrawable ad = (AnimationDrawable) ivLoading.getDrawable();
            if (ad != null && !ad.isRunning()) {
                ad.start();
            }
        }
    }

    @Override
    public void hideLoading() {
        if (mBaseContentContainer != null && mLoadingView != null) {
            mBaseContentContainer.removeView(mLoadingView);
            mLoadingView = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
