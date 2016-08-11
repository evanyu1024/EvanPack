package com.evan.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.IBaseView;
import com.evan.demo.manager.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by evanyu on 16/6/12.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(inflater, container);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void finishView() {
        // empty
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
