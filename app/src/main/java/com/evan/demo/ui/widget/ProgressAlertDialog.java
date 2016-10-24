package com.evan.demo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.evan.demo.R;

/**
 * 带进度显示的提示对话框
 * Created by evanyu on 16/8/18.
 */
public class ProgressAlertDialog extends AlertDialog {

    private FrameLayout mRootView;
    private View mProgressView;

    private ProgressAlertDialog(Context context, FrameLayout rootView) {
        super(context);
        this.mRootView = rootView;
        this.setView(rootView);
        this.setCancelable(false); // 默认不可点击返回键关闭Dialog

        // 在关闭dialog时默认隐藏进度提示
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                hideProgressBar();
            }
        });
    }

    public static ProgressAlertDialog newInstance(Activity context, int layoutResId) {
        if (layoutResId <= 0) {
            throw new IllegalArgumentException("layoutResId must be greater than zero");
        }
        // 初始化Dialog中显示的View
        LayoutInflater inflater = LayoutInflater.from(context);
        FrameLayout rootView = (FrameLayout) inflater.inflate(R.layout.base_container, null);
        rootView.removeAllViews();
        inflater.inflate(layoutResId, rootView, true);

        return new ProgressAlertDialog(context, rootView);
    }

    public void showProgressBar() {
        if (mRootView != null && mProgressView == null) {
            mProgressView = LayoutInflater.from(getContext()).inflate(R.layout.common_progress, mRootView, true);
            ImageView loadingView = (ImageView) mProgressView.findViewById(R.id.iv_loading);
            AnimationDrawable ad = (AnimationDrawable) loadingView.getDrawable();
            if (ad != null && !ad.isRunning()) {
                ad.start();
            }
        }
    }

    public void hideProgressBar() {
        if (mRootView != null && mProgressView != null) {
            mRootView.removeView(mProgressView);
            mProgressView = null;
        }
    }

    public View getContentView() {
        return mRootView;
    }

    public View findViewById(int resId) {
        return mRootView.findViewById(resId);
    }

}
