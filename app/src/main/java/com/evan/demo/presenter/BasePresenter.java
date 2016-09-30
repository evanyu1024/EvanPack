package com.evan.demo.presenter;

import com.evan.demo.ui.iview.IBaseView;

/**
 * Created by evanyu on 16/7/14.
 */
public abstract class BasePresenter<T extends IBaseView> implements IPresenter<T> {

    protected T mView;

    public BasePresenter(T view) {
        attachView(view);
    }

    /**
     * 启动Presenter,初始化数据
     */
    public abstract void start();

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public T getView() {
        return mView;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void showToast(String msg) {
        mView.showMessage(msg);
    }

    @Override
    public void showLoading() {
        mView.showLoading();
    }

    @Override
    public void hideLoading() {
        mView.hideLoading();
    }

    public void onViewDestroy() {
        detachView();
    }

}
