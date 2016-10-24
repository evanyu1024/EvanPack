package com.evan.demo.presenter;

import android.content.Context;
import android.content.Intent;

import com.evan.demo.ui.iview.IBaseView;

/**
 * Presenter基类
 * Created by evanyu on 16/9/8.
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected T mView;
    protected Context mContext;

    /**
     * 构造方法
     */
    private BasePresenter() {
    }

    /**
     * 构造方法,同时绑定指定View
     **/
    public BasePresenter(T view) {
        if(view == null) {
            throw new RuntimeException("view must be not null");
        }
        attachView(view);
        mContext = view.getContext();
    }

    /**
     * 获取当前已关联的View
     *
     * @return
     */
    public T getView() {
        return mView;
    }

    /**
     * 关联指定View
     */
    public void attachView(T view) {
        mView = view;
        onAttachView();
    }

    /**
     * 取消与View的关联
     */
    public void detatchView() {
        if (mView != null) {
            mView = null;
            onDetachView();
        }
    }

    /**
     * 当成功关联View时调用
     */
    public void onAttachView() {
        // empty
    }

    /**
     * 当成功取消关联View时调用
     */
    public void onDetachView() {
        // empty
    }

    /**
     * 判断是否已经关联View
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 显示Toast
     * {@link IBaseView#showToast(String)}
     */
    public void showToast(String text) {
        mView.showToast(text);
    }

    /**
     * 显示View中的进度提示
     * {@link IBaseView#showLoading()}
     */
    public void showLoading() {
        if (isViewAttached()) {
            mView.showLoading();
        }
    }

    /**
     * 隐藏View中的进度提示
     * {@link IBaseView#hideLoading()}
     */
    public void hideLoading() {
        if (isViewAttached()) {
            mView.hideLoading();
        }
    }

    /**
     * 当View销毁时调用
     */
    public void onViewDestory() {
        detatchView();
    }

    /**
     * 当Activity获取到返回值时调用
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
