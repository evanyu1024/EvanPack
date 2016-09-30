package com.evan.demo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.iview.IBaseView;
import com.evan.demo.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by evanyu on 16/6/12.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    protected Bundle mSavedInstanceState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSavedInstanceState = savedInstanceState;
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
//        return null;
//    }

    public abstract int getLayoutResId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onViewCreated(view);
    }

    public abstract void onViewCreated(View view);

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
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void finishView() {
        // empty
    }

}
